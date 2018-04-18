unit UCadastroAtores;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, ExtCtrls, cxStyles, cxCustomData, cxGraphics,
  cxFilter, cxData, cxDataStorage, cxEdit, DB, cxDBData, Menus,
  cxGridLevel, cxClasses, cxControls, cxGridCustomView,
  cxGridCustomTableView, cxGridTableView, cxGridDBTableView, cxGrid,
  Buttons, ZAbstractRODataset, ZAbstractDataset, ZDataset, cxTextEdit;


type Tnacionalidade = record

   nacionalidade: string;
  id :integer;

end;

type
  TfrmCadastroAtores = class(TForm)
    Panel1: TPanel;
    Image1: TImage;
    Label1: TLabel;
    GroupBox1: TGroupBox;
    edtNome: TLabeledEdit;
    edtSobrenome: TLabeledEdit;
    cbNacionalidade: TComboBox;
    lblNacionalidade: TLabel;
    btnSalvar: TBitBtn;
    BitBtn1: TBitBtn;
    GroupBox2: TGroupBox;
    cxGrid1: TcxGrid;
    grdAtores: TcxGridDBTableView;
    cxGrid1Level1: TcxGridLevel;
    PopupMenu1: TPopupMenu;
    Desativarregistro1: TMenuItem;

    DataSource1: TDataSource;
    grdAtoresDBColumn1: TcxGridDBColumn;
    grdAtoresDBColumn2: TcxGridDBColumn;
    zGen: TZQuery;
    grdAtoresDBColumn3: TcxGridDBColumn;
    edtBusca: TLabeledEdit;
    cbTipo: TComboBox;
    zConsultaAtores: TZQuery;
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure btnSalvarClick(Sender: TObject);
    procedure FormShow(Sender: TObject);
    procedure Desativarregistro1Click(Sender: TObject);
    procedure edtBuscaChange(Sender: TObject);
  private

    arrNacionalidade : array of Tnacionalidade;
    procedure AlimentaGridAtores;
    procedure alimentaComboNacionalidade;
    function existeJacadastrado(nome, sobrenome:string):boolean;
    { Private declarations }
  public
    { Public declarations }
  end;

var
  frmCadastroAtores: TfrmCadastroAtores;

implementation

uses UDataModule;

{$R *.dfm}

procedure TfrmCadastroAtores.FormClose(Sender: TObject;
  var Action: TCloseAction);
begin

  action := cafree;
  frmCadastroAtores := nil;


end;

procedure TfrmCadastroAtores.btnSalvarClick(Sender: TObject);
begin

  if trim(edtnome.text) = emptystr then
  begin
    edtnome.setfocus;
    raise exception.create('O nome é obrigatório');
  end;

  if cbNacionalidade.ItemIndex = -1 then
  begin
    cbNacionalidade.SetFocus;
    raise exception.create('Selecione a nacionalidade');
  end;

  if existeJacadastrado(edtnome.text, edtSobrenome.Text) then
  begin
    raise exception.create('Este Ator já está cadastrado');
  end;


  try
    zGen.sql.clear;
    zGen.sql.add('INSERT INTO tblAtores');
    zGen.sql.add('(');
    zGen.sql.add('  nome,');
    zGen.sql.add('  sobrenome,');
    zGen.sql.add('  idNacionalidade');
    zGen.sql.add(') ');
    zGen.sql.add('VALUES (');
    zGen.sql.add('  :nome,');
    zGen.sql.add('  :sobrenome,');
    zGen.sql.add('  :idNacionalidade');
    zGen.sql.add(')');
    zGen.parambyname('nome').asstring := trim(edtnome.text);
    zGen.parambyname('sobrenome').asstring := trim(edtSobrenome.text);
    zGen.parambyname('idNacionalidade').AsInteger := arrNacionalidade[cbNacionalidade.ItemIndex].id;
    zGen.ExecSQL;
  except
    on e : exception do
    begin
      raise exception.create('Deu problema ao inserir o dado , classe: '+ e.Message);
    end;
  end;

  showmessage('Operação concluída com sucesso') ;
  edtNome.Clear;
  edtSobrenome.Clear;
  cbNacionalidade.itemindex := -1;
  edtnome.SetFocus;
  zConsultaAtores.Refresh;


end;

procedure TfrmCadastroAtores.AlimentaGridAtores;
begin
  zConsultaAtores.sql.clear;
  zConsultaAtores.sql.add('select');
  zConsultaAtores.sql.add('a.idCadastro, upper(a.nome)nome, upper(a.sobrenome)sobrenome');
  zConsultaAtores.sql.add('from');
  zConsultaAtores.sql.add('tblAtores a');
  zConsultaAtores.sql.add('where');
  zConsultaAtores.sql.add('a.ativo = 1');
  zConsultaAtores.sql.add('order by upper(a.nome) asc,upper(a.sobrenome) asc ');
  zConsultaAtores.open;
end;

procedure TfrmCadastroAtores.FormShow(Sender: TObject);
begin
  AlimentaGridAtores;
  alimentaComboNacionalidade;
end;

procedure TfrmCadastroAtores.alimentaComboNacionalidade;
begin

  cbNacionalidade.Clear;
  arrNacionalidade := nil;

  zGen.sql.clear;
  zGen.sql.add('select ');
  zGen.sql.add('upper(a.nacionalidade)nacionalidade, a.idcadastro');
  zGen.sql.add('from');
  zGen.sql.add('tblNacionalidades a');
  zGen.sql.add('where');
  zGen.sql.add('a.ativo = 1');
  zGen.sql.add('order by upper(a.nacionalidade) asc');
  zGen.open;

  zGen.First;
  while not zGen.Eof do
  begin
    setlength(arrNacionalidade, length(arrNacionalidade)+1);
    arrNacionalidade[length(arrNacionalidade)-1].id := zGen.fieldbyname('idCadastro').AsInteger;
    arrNacionalidade[length(arrNacionalidade)-1].nacionalidade := zGen.fieldbyname('nacionalidade').asstring;
    cbNacionalidade.Items.Add(zGen.fieldbyname('nacionalidade').asstring);
    zGen.next;
  end;


end;

function TfrmCadastroAtores.existeJacadastrado(nome,
  sobrenome: string): boolean;
begin

  result := false;

  zGen.sql.clear;
  zGen.sql.add('select');
  zGen.sql.add('*');
  zGen.sql.add('from');
  zGen.sql.add('tblAtores a');
  zGen.sql.add('where');
  zGen.sql.add('dbo.RetiraAcento(replace(upper(a.nome) + upper(a.sobrenome),'+ quotedstr(' ')+ ','+ quotedstr('')+')) = dbo.RetiraAcento(replace(upper(:nome),'+quotedstr(' ')+','+ quotedstr('')+')) ');
  zGen.sql.add('and a.ativo = 1');
  zGen.parambyname('nome').asstring := nome+ sobrenome;

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

procedure TfrmCadastroAtores.Desativarregistro1Click(Sender: TObject);
begin
  if grdAtores.datacontroller.recordcount = 0 then
  begin
    exit;
  end;

  try
    zGen.sql.clear;
    zGen.sql.add('update');
    zGen.sql.add('tblAtores');
    zGen.sql.add('set tblAtores.ativo = 0, ');
    zGen.sql.add('tblAtores.dtDesativa = GETDATE()');
    zGen.sql.add('where');
    zGen.sql.add('tblAtores.idCadastro = :idAtor');
    zGen.parambyname('idAtor').asinteger := zConsultaAtores.fieldbyname('idCadastro').asinteger;
    //zGen.parambyname('idDiretor').asinteger := grdDiretores.datacontroller.values[grdDiretores.DataController.FocusedRecordIndex,2] ;
    zGen.ExecSQL;

  except
    on e :exception do
    begin
      raise exception.create('Falha ao atualizar registro : '+ e.Message);
    end

  end;

  showmessage('Registro desativado com sucesso');
  zConsultaAtores.Refresh;

end;

procedure TfrmCadastroAtores.edtBuscaChange(Sender: TObject);
begin
  zConsultaAtores.Locate(cbTipo.Text, trim(edtBusca.Text),[loCaseInsensitive, loPartialKey])
end;

end.
