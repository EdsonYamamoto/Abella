unit UCadastroNacionalidades;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, ExtCtrls, cxStyles, cxCustomData, cxGraphics,
  cxFilter, cxData, cxDataStorage, cxEdit, DB, cxDBData, Menus,
  cxGridLevel, cxClasses, cxControls, cxGridCustomView,
  cxGridCustomTableView, cxGridTableView, cxGridDBTableView, cxGrid,
  Buttons, ZAbstractRODataset, ZAbstractDataset, ZDataset, cxTextEdit;


type
  TfrmCadastroNacionalidades = class(TForm)
    Panel1: TPanel;
    Image1: TImage;
    Label1: TLabel;
    GroupBox1: TGroupBox;
    edtNacionalidade: TLabeledEdit;
    btnSalvar: TBitBtn;
    BitBtn1: TBitBtn;
    GroupBox2: TGroupBox;
    cxGrid1: TcxGrid;
    grdNacionalidade: TcxGridDBTableView;
    cxGrid1Level1: TcxGridLevel;
    PopupMenu1: TPopupMenu;
    Desativarregistro1: TMenuItem;

    DataSource1: TDataSource;
    grdNacionalidadeDBColumn1: TcxGridDBColumn;
    zGen: TZQuery;
    grdNacionalidadeDBColumn3: TcxGridDBColumn;
    edtBusca: TLabeledEdit;
    zConsultaNacionalidades: TZQuery;
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure btnSalvarClick(Sender: TObject);
    procedure FormShow(Sender: TObject);
    procedure Desativarregistro1Click(Sender: TObject);
    procedure edtBuscaChange(Sender: TObject);
    procedure edtNacionalidadeKeyPress(Sender: TObject; var Key: Char);
  private

    procedure AlimentaGridNacionalidade;
    function existeJacadastrado(Nacionalidade:string):boolean;
    { Private declarations }
  public
    { Public declarations }
  end;

var
  frmCadastroNacionalidades: TfrmCadastroNacionalidades;

implementation

uses UDataModule;

{$R *.dfm}

procedure TfrmCadastroNacionalidades.FormClose(Sender: TObject;
  var Action: TCloseAction);
begin

  action := cafree;
  frmCadastroNacionalidades := nil;


end;

procedure TfrmCadastroNacionalidades.btnSalvarClick(Sender: TObject);
begin

  if trim(edtNacionalidade.text) = emptystr then
  begin
    edtNacionalidade.setfocus;
    raise exception.create('A nacionalidade é obrigatória');
  end;


  if existeJacadastrado(edtNacionalidade.text) then
  begin
    raise exception.create('Esta nacionalidade já está cadastrada');
  end;


  try
    zGen.sql.clear;
    zGen.sql.add('INSERT INTO tblNacionalidades');
    zGen.sql.add('(');
    zGen.sql.add('  nacionalidade');
    zGen.sql.add(') ');
    zGen.sql.add('VALUES (');
    zGen.sql.add('  :nacionalidade');
    zGen.sql.add(')');
    zGen.parambyname('nacionalidade').asstring := trim(edtNacionalidade.text);
    zGen.ExecSQL;
  except
    on e : exception do
    begin
      raise exception.create('Deu problema ao inserir o dado , classe: '+ e.Message);
    end;
  end;

  showmessage('Operação concluída com sucesso') ;
  edtNacionalidade.Clear;
  edtNacionalidade.SetFocus;
  zConsultaNacionalidades.Refresh;


end;

procedure TfrmCadastroNacionalidades.AlimentaGridNacionalidade;
begin
  zConsultaNacionalidades.sql.clear;
  zConsultaNacionalidades.sql.add('select');
  zConsultaNacionalidades.sql.add('a.idCadastro, upper(a.nacionalidade)nacionalidade');
  zConsultaNacionalidades.sql.add('from');
  zConsultaNacionalidades.sql.add('tblNacionalidades a');
  zConsultaNacionalidades.sql.add('where');
  zConsultaNacionalidades.sql.add('a.ativo = 1');
  zConsultaNacionalidades.sql.add('order by upper(a.nacionalidade) asc ');
  zConsultaNacionalidades.open;
end;

procedure TfrmCadastroNacionalidades.FormShow(Sender: TObject);
begin
  AlimentaGridNacionalidade;
end;



function TfrmCadastroNacionalidades.existeJacadastrado(nacionalidade: string): boolean;
begin

  result := false;

  zGen.sql.clear;
  zGen.sql.add('select');
  zGen.sql.add('*');
  zGen.sql.add('from');
  zGen.sql.add('tblNacionalidades a');
  zGen.sql.add('where');
  zGen.sql.add('dbo.RetiraAcento(replace(upper(a.nacionalidade),'+ quotedstr(' ')+ ','+ quotedstr('')+')) = dbo.RetiraAcento(replace(upper(:nacionalidade),'+quotedstr(' ')+','+ quotedstr('')+')) ');
  zGen.sql.add('and a.ativo = 1');
  zGen.parambyname('nacionalidade').asstring := nacionalidade;

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

procedure TfrmCadastroNacionalidades.Desativarregistro1Click(Sender: TObject);
begin

  if grdNacionalidade.datacontroller.recordcount = 0 then
  begin
    exit;
  end;


  try
    zGen.sql.clear;
    zGen.sql.add('update');
    zGen.sql.add('tblNacionalidades');
    zGen.sql.add('set tblNacionalidades.ativo = 0, ');
    zGen.sql.add('tblNacionalidades.dtDesativa = GETDATE()');
    zGen.sql.add('where');
    zGen.sql.add('tblNacionalidades.idCadastro = :idNacionalidade');
    zGen.parambyname('idNacionalidade').asinteger := zConsultaNacionalidades.fieldbyname('idCadastro').asinteger;
    
    zGen.ExecSQL;

  except
    on e :exception do
    begin
      raise exception.create('Falha ao atualizar registro : '+ e.Message);
    end

  end;

  showmessage('Registro desativado com sucesso');
  zConsultaNacionalidades.Refresh;

end;

procedure TfrmCadastroNacionalidades.edtBuscaChange(Sender: TObject);
begin
  zConsultaNacionalidades.Locate('nacionalidade', trim(edtBusca.Text),[loCaseInsensitive, loPartialKey])
end;

procedure TfrmCadastroNacionalidades.edtNacionalidadeKeyPress(
  Sender: TObject; var Key: Char);
begin
  if key = #13 then
  begin
    btnSalvar.Click;
  end;
end;

end.
