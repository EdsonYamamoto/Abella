unit UcadastroDiretores;

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
  TfrmCadastroDiretores = class(TForm)
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
    grdDiretores: TcxGridDBTableView;
    cxGrid1Level1: TcxGridLevel;
    PopupMenu1: TPopupMenu;
    Desativarregistro1: TMenuItem;
    zConsultaDiretores: TZQuery;
    DataSource1: TDataSource;
    grdDiretoresDBColumn1: TcxGridDBColumn;
    grdDiretoresDBColumn2: TcxGridDBColumn;
    zGen: TZQuery;
    grdDiretoresDBColumn3: TcxGridDBColumn;
    edtBusca: TLabeledEdit;
    cbTipo: TComboBox;
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure btnSalvarClick(Sender: TObject);
    procedure FormShow(Sender: TObject);
    procedure Desativarregistro1Click(Sender: TObject);
    procedure edtBuscaChange(Sender: TObject);
  private

    arrNacionalidade : array of Tnacionalidade;
    procedure alimentaGridDiretores;
    procedure alimentaComboNacionalidade;
    function existeJacadastrado(nome, sobrenome:string):boolean;
    { Private declarations }
  public
    { Public declarations }
  end;

var
  frmCadastroDiretores: TfrmCadastroDiretores;

implementation

uses UDataModule;

{$R *.dfm}

procedure TfrmCadastroDiretores.FormClose(Sender: TObject;
  var Action: TCloseAction);
begin
{


aqui tem um comentario

/*asdkasbd
(*Y@*&#&*$¨@!¨#!*%$(

}
  action := cafree;
  frmCadastroDiretores := nil;


end;

procedure TfrmCadastroDiretores.btnSalvarClick(Sender: TObject);
begin

  if trim(edtnome.text) = emptystr then
  begin
    edtnome.setfocus;
    raise exception.create('O nome é obrigatório');
  end;
	else if trim(edtnome.text) = emptystr then
	begin
	showmessage('fora');
	end;
	else
	showmessage('bolafora');

  if cbNacionalidade.ItemIndex = -1 then
  begin
    cbNacionalidade.SetFocus;
    raise exception.create('Selecione a nacionalidade');
  end;

  if existeJacadastrado(edtnome.text, edtSobrenome.Text) then
  begin
    raise exception.create('Este diretor já está cadastrado');
  end;


  try
    zGen.sql.clear;
    zGen.sql.add('INSERT INTO tblDiretores');
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
  zConsultaDiretores.Refresh;


end;

procedure TfrmCadastroDiretores.alimentaGridDiretores;
begin
  zConsultaDiretores.sql.clear;
  zConsultaDiretores.sql.add('select');
  zConsultaDiretores.sql.add('a.idCadastro, upper(a.nome)nome, upper(a.sobrenome)sobrenome');
  zConsultaDiretores.sql.add('from');
  zConsultaDiretores.sql.add('tblDiretores a');
  zConsultaDiretores.sql.add('where');
  zConsultaDiretores.sql.add('a.ativo = 1');
  zConsultaDiretores.sql.add('order by upper(a.nome) asc,upper(a.sobrenome) asc ');
  zConsultaDiretores.open;
end;

procedure TfrmCadastroDiretores.FormShow(Sender: TObject);
begin
  alimentaGridDiretores;
  alimentaComboNacionalidade;
end;

procedure TfrmCadastroDiretores.alimentaComboNacionalidade;
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

function TfrmCadastroDiretores.existeJacadastrado(nome,
  sobrenome: string): boolean;
begin

  result := false;

  zGen.sql.clear;
  zGen.sql.add('select');
  zGen.sql.add('*');
  zGen.sql.add('from');
  zGen.sql.add('tblDiretores a');
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

procedure TfrmCadastroDiretores.Desativarregistro1Click(Sender: TObject);
begin

  if grdDiretores.datacontroller.recordcount = 0 then
  begin
    exit;
  end;


  try
    zGen.sql.clear;
    zGen.sql.add('update');
    zGen.sql.add('tblDiretores');
    zGen.sql.add('set tblDiretores.ativo = 0, ');
    zGen.sql.add('tblDiretores.dtDesativa = GETDATE()');
    zGen.sql.add('where');
    zGen.sql.add('tblDiretores.idCadastro = :idDiretor');
    zGen.parambyname('idDiretor').asinteger := zConsultaDiretores.fieldbyname('idCadastro').asinteger;
    //zGen.parambyname('idDiretor').asinteger := grdDiretores.datacontroller.values[grdDiretores.DataController.FocusedRecordIndex,2] ;
    zGen.ExecSQL;

  except
    on e :exception do
    begin
      raise exception.create('Falha ao atualizar registro : '+ e.Message);
    end

  end;

  showmessage('Registro desativado com sucesso');
  zConsultaDiretores.Refresh;

end;

procedure TfrmCadastroDiretores.edtBuscaChange(Sender: TObject);
begin
  zConsultaDiretores.Locate(cbTipo.Text, trim(edtBusca.Text),[loCaseInsensitive, loPartialKey])
end;

end.
