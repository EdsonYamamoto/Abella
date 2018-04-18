unit UcadastroGeneros;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, ExtCtrls, cxStyles, cxCustomData, cxGraphics,
  cxFilter, cxData, cxDataStorage, cxEdit, DB, cxDBData, Menus,
  cxGridLevel, cxClasses, cxControls, cxGridCustomView,
  cxGridCustomTableView, cxGridTableView, cxGridDBTableView, cxGrid,
  Buttons, ZAbstractRODataset, ZAbstractDataset, ZDataset, cxTextEdit;


type
  TfrmCadastroGeneros = class(TForm)
    Panel1: TPanel;
    Image1: TImage;
    Label1: TLabel;
    GroupBox1: TGroupBox;
    edtGenero: TLabeledEdit;
    btnSalvar: TBitBtn;
    BitBtn1: TBitBtn;
    GroupBox2: TGroupBox;
    cxGrid1: TcxGrid;
    grdGeneros: TcxGridDBTableView;
    cxGrid1Level1: TcxGridLevel;
    PopupMenu1: TPopupMenu;
    Desativarregistro1: TMenuItem;

    DataSource1: TDataSource;
    grdGenerosDBColumn1: TcxGridDBColumn;
    zGen: TZQuery;
    grdGenerosDBColumn3: TcxGridDBColumn;
    edtBusca: TLabeledEdit;
    zConsultaGeneros: TZQuery;
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure btnSalvarClick(Sender: TObject);
    procedure FormShow(Sender: TObject);
    procedure Desativarregistro1Click(Sender: TObject);
    procedure edtBuscaChange(Sender: TObject);
    procedure edtGeneroKeyPress(Sender: TObject; var Key: Char);
  private

    procedure AlimentaGridGeneros;
    function existeJacadastrado(genero:string):boolean;
    { Private declarations }
  public
    { Public declarations }
  end;

var
  frmCadastroGeneros: TfrmCadastroGeneros;

implementation

uses UDataModule;

{$R *.dfm}

procedure TfrmCadastroGeneros.FormClose(Sender: TObject;
  var Action: TCloseAction);
begin

  action := cafree;
  frmCadastroGeneros := nil;


end;

procedure TfrmCadastroGeneros.btnSalvarClick(Sender: TObject);
begin

  if trim(edtGenero.text) = emptystr then
  begin
    edtGenero.setfocus;
    raise exception.create('O Genero é obrigatório');
  end;


  if existeJacadastrado(edtGenero.text) then
  begin
    raise exception.create('Este Genero já está cadastrado');
  end;


  try
    zGen.sql.clear;
    zGen.sql.add('INSERT INTO tblgeneros');
    zGen.sql.add('(');
    zGen.sql.add('  genero');
    zGen.sql.add(') ');
    zGen.sql.add('VALUES (');
    zGen.sql.add('  :genero');
    zGen.sql.add(')');
    zGen.parambyname('genero').asstring := trim(edtGenero.text);
    zGen.ExecSQL;
  except
    on e : exception do
    begin
      raise exception.create('Deu problema ao inserir o dado , classe: '+ e.Message);
    end;
  end;

  showmessage('Operação concluída com sucesso') ;
  edtGenero.Clear;
  edtGenero.setfocus;
  zConsultaGeneros.Refresh;


end;

procedure TfrmCadastroGeneros.AlimentaGridGeneros;
begin
  zConsultaGeneros.sql.clear;
  zConsultaGeneros.sql.add('select');
  zConsultaGeneros.sql.add('a.idCadastro, upper(a.genero)genero');
  zConsultaGeneros.sql.add('from');
  zConsultaGeneros.sql.add('tblGeneros a');
  zConsultaGeneros.sql.add('where');
  zConsultaGeneros.sql.add('a.ativo = 1');
  zConsultaGeneros.sql.add('order by upper(a.genero) asc ');
  zConsultaGeneros.open;
end;

procedure TfrmCadastroGeneros.FormShow(Sender: TObject);
begin
  AlimentaGridGeneros;
end;



function TfrmCadastroGeneros.existeJacadastrado(genero: string): boolean;
begin

  result := false;

  zGen.sql.clear;
  zGen.sql.add('select');
  zGen.sql.add('*');
  zGen.sql.add('from');
  zGen.sql.add('tblGeneros a');
  zGen.sql.add('where');
  zGen.sql.add('dbo.RetiraAcento(replace(upper(a.genero),'+ quotedstr(' ')+ ','+ quotedstr('')+')) = dbo.RetiraAcento(replace(upper(:genero),'+quotedstr(' ')+','+ quotedstr('')+')) ');
  zGen.sql.add('and a.ativo = 1');
  zGen.parambyname('genero').asstring := genero;

  {zGen.sql.add('select dbo.fnValidaDiretorCadastrado(:nome,:sobrenome) existe');
  zgen.ParamByName('nome').asstring := nome;
  zgen.ParamByName('sobrenome').asstring := sobrenome; }
  zGen.open;


  //if zGen.fieldbyname('existe').asboolean  then
  if zgen.recordcount>0 then
  begin
    result := true;
  end;




end;

procedure TfrmCadastroGeneros.Desativarregistro1Click(Sender: TObject);
begin

  if grdGeneros.datacontroller.recordcount = 0 then
  begin
    exit;
  end;


  try
    zGen.sql.clear;
    zGen.sql.add('update');
    zGen.sql.add('tblGeneros');
    zGen.sql.add('set tblGeneros.ativo = 0, ');
    zGen.sql.add('tblGeneros.dtDesativa = GETDATE()');
    zGen.sql.add('where');
    zGen.sql.add('tblGeneros.idCadastro = :idGenero');
    zGen.parambyname('idGenero').asinteger := zConsultaGeneros.fieldbyname('idCadastro').asinteger;
    
    zGen.ExecSQL;

  except
    on e :exception do
    begin
      raise exception.create('Falha ao atualizar registro : '+ e.Message);
    end

  end;

  showmessage('Registro desativado com sucesso');
  zConsultaGeneros.Refresh;

end;

procedure TfrmCadastroGeneros.edtBuscaChange(Sender: TObject);
begin
  zConsultaGeneros.Locate('genero', trim(edtBusca.Text),[loCaseInsensitive, loPartialKey])
end;

procedure TfrmCadastroGeneros.edtGeneroKeyPress(Sender: TObject;
  var Key: Char);
begin
  if key = #13 then
  begin
    btnSalvar.Click;
  end;


end;

end.
