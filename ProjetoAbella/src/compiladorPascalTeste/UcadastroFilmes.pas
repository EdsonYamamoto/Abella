unit UCadastroFilmes;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, ExtCtrls, cxStyles, cxCustomData, cxGraphics,
  cxFilter, cxData, cxDataStorage, cxEdit, DB, cxDBData, Menus,
  cxGridLevel, cxClasses, cxControls, cxGridCustomView,
  cxGridCustomTableView, cxGridTableView, cxGridDBTableView, cxGrid,
  Buttons, ZAbstractRODataset, ZAbstractDataset, ZDataset, cxTextEdit,
  ComCtrls, ActnList;


type
  TfrmCadastroFilmes = class(TForm)
    Panel1: TPanel;
    Image1: TImage;
    Label1: TLabel;
    PopupMenu1: TPopupMenu;
    Desativarregistro1: TMenuItem;

    DataSource1: TDataSource;
    zGen: TZQuery;
    zConsultafilmes: TZQuery;
    BitBtn1: TBitBtn;
    btnSalvar: TBitBtn;
    PageControl1: TPageControl;
    TabSheet1: TTabSheet;
    TabSheet2: TTabSheet;
    GroupBox1: TGroupBox;
    edtNome: TLabeledEdit;
    edtAno: TLabeledEdit;
    edtCodigoMundial: TLabeledEdit;
    edtTempoDuracao: TLabeledEdit;
    GroupBox3: TGroupBox;
    cxGrid2: TcxGrid;
    tblAtores: TcxGridTableView;
    cxGrid2Level1: TcxGridLevel;
    cxGrid3: TcxGrid;
    tblAtoresSelecionados: TcxGridTableView;
    cxGridLevel1: TcxGridLevel;
    memoSinopse: TMemo;
    Label2: TLabel;
    TabSheet3: TTabSheet;
    GroupBox4: TGroupBox;
    cxGrid4: TcxGrid;
    tblDiretores: TcxGridTableView;
    cxGridLevel2: TcxGridLevel;
    cxGrid5: TcxGrid;
    tblDiretoresSelecionados: TcxGridTableView;
    cxGridLevel3: TcxGridLevel;
    TabSheet4: TTabSheet;
    GroupBox5: TGroupBox;
    cxGrid6: TcxGrid;
    tblGeneros: TcxGridTableView;
    cxGridLevel4: TcxGridLevel;
    cxGrid7: TcxGrid;
    tblGenerosSelecionados: TcxGridTableView;
    cxGridLevel5: TcxGridLevel;
    tblAtoresColumn1: TcxGridColumn;
    tblAtoresColumn2: TcxGridColumn;
    tblAtoresSelecionadosColumn1: TcxGridColumn;
    tblAtoresSelecionadosColumn2: TcxGridColumn;
    tblDiretoresColumn1: TcxGridColumn;
    tblDiretoresColumn2: TcxGridColumn;
    tblDiretoresSelecionadosColumn1: TcxGridColumn;
    tblDiretoresSelecionadosColumn2: TcxGridColumn;
    tblGenerosColumn1: TcxGridColumn;
    tblGenerosColumn2: TcxGridColumn;
    tblGenerosSelecionadosColumn1: TcxGridColumn;
    tblGenerosSelecionadosColumn2: TcxGridColumn;
    TabSheet5: TTabSheet;
    GroupBox2: TGroupBox;
    cxGrid1: TcxGrid;
    grdFilmes: TcxGridDBTableView;
    grdFilmesDBColumn1: TcxGridDBColumn;
    grdFilmesDBColumn2: TcxGridDBColumn;
    grdFilmesDBColumn3: TcxGridDBColumn;
    cxGrid1Level1: TcxGridLevel;
    Label3: TLabel;
    ActionList1: TActionList;
    aba2: TAction;
    Action1: TAction;
    Action2: TAction;
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure btnSalvarClick(Sender: TObject);
    procedure FormShow(Sender: TObject);
    procedure Desativarregistro1Click(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure tblAtoresDblClick(Sender: TObject);
    procedure edtAnoKeyPress(Sender: TObject; var Key: Char);
    procedure edtCodigoMundialKeyPress(Sender: TObject; var Key: Char);
    procedure edtTempoDuracaoKeyPress(Sender: TObject; var Key: Char);
    procedure tblAtoresSelecionadosDblClick(Sender: TObject);
    procedure tblDiretoresDblClick(Sender: TObject);
    procedure tblDiretoresSelecionadosDblClick(Sender: TObject);
    procedure tblGenerosDblClick(Sender: TObject);
    procedure tblGenerosSelecionadosDblClick(Sender: TObject);
    procedure aba2Execute(Sender: TObject);
    procedure tblAtoresKeyPress(Sender: TObject; var Key: Char);
    procedure Action1Execute(Sender: TObject);
    procedure Action2Execute(Sender: TObject);
    procedure tblAtoresSelecionadosKeyPress(Sender: TObject;
      var Key: Char);
  private
    function existeJacadastrado(nome:string):boolean;
    procedure alimentaAtores;
    procedure alimentadiretores;
    procedure alimentaGeneros;
    procedure begintransaction;
    procedure committransaction;
    procedure rollbacktransaction(msg:string);
    procedure clear;
    function insertFilme:integer;
    procedure insertVincFilmeDiretor(idFilme:integer);
    procedure insertVincFilmeAtor(idFilme:integer);
    procedure insertVincFilmeGenero(idFilme:integer);
    procedure alimentaFilmes;
    { Private declarations }
  public
    { Public declarations }
  end;

var
  frmCadastroFilmes: TfrmCadastroFilmes;

implementation

uses UDataModule;

{$R *.dfm}

procedure TfrmCadastroFilmes.FormClose(Sender: TObject;
  var Action: TCloseAction);
begin

  action := cafree;
  frmCadastroFilmes := nil;


end;

procedure TfrmCadastroFilmes.btnSalvarClick(Sender: TObject);
var idFilme:integer;
begin

  if trim(edtnome.text) = emptystr then
  begin
    edtnome.setfocus;
    raise exception.create('O nome é obrigatório');
  end;

  if trim(edtAno.text) = emptystr then
  begin
    edtAno.setfocus;
    raise exception.create('O ano é obrigatório');
  end;

  if trim(edtCodigoMundial.text) = emptystr then
  begin
    edtCodigoMundial.setfocus;
    raise exception.create('O Código Mundial é obrigatório');
  end;

  if trim(edtTempoDuracao.text) = emptystr then
  begin
    edtTempoDuracao.setfocus;
    raise exception.create('O Tempo de duração é obrigatório');
  end;

  if tblAtoresSelecionados.DataController.RecordCount = 0 then
  begin
    PageControl1.TabIndex := 1;
    raise exception.create('Selecione ao menos um ator');
  end;

  if tblDiretoresSelecionados.DataController.RecordCount = 0 then
  begin
    PageControl1.TabIndex := 2;
    raise exception.create('Selecione ao menos um diretor');
  end;

  if tblGenerosSelecionados.DataController.RecordCount = 0 then
  begin
    PageControl1.TabIndex := 3;
    raise exception.create('Selecione ao menos um genero');
  end;


  if existeJacadastrado(edtnome.text) then
  begin
    raise exception.create('Este Filme já está cadastrado');
  end;


  try
    begintransaction;

    idFilme := insertFilme;

    insertVincFilmeDiretor(idFilme);
    insertVincFilmeAtor(idFilme);
    insertVincFilmeGenero(idfilme);

    commitTransaction;

  except
    on e : exception do
    begin
      rollbacktransaction(e.message);
      raise exception.create('Erro ao inserir filme : '+ e.Message);
    end;
  end;

  showmessage('Operação concluída com sucesso') ;
  clear;



end;



procedure TfrmCadastroFilmes.FormShow(Sender: TObject);
begin

  PageControl1.TabIndex := 0;
  alimentaAtores;
  alimentadiretores;
  alimentaGeneros;
  alimentaFilmes;
  edtNome.SetFocus;
end;



function TfrmCadastroFilmes.existeJacadastrado(nome: string): boolean;
begin

  result := false;

  zGen.sql.clear;
  zGen.sql.add('select');
  zGen.sql.add('*');
  zGen.sql.add('from');
  zGen.sql.add('tblfilmes a');
  zGen.sql.add('where');
  zGen.sql.add('dbo.RetiraAcento(replace(upper(a.nome),'+ quotedstr(' ')+ ','+ quotedstr('')+')) = dbo.RetiraAcento(replace(upper(:nome),'+quotedstr(' ')+','+ quotedstr('')+')) ');
  zGen.sql.add('and a.ativo = 1');
  zGen.parambyname('nome').asstring := nome;
  zGen.open;
  if zgen.recordcount>0 then
  begin
    result := true;
  end;




end;

procedure TfrmCadastroFilmes.Desativarregistro1Click(Sender: TObject);
begin
  if grdFilmes.datacontroller.recordcount = 0 then
  begin
    exit;
  end;

  try
    zGen.sql.clear;
    zGen.sql.add('update');
    zGen.sql.add('tblfilmes');
    zGen.sql.add('set tblfilmes.ativo = 0, ');
    zGen.sql.add('tblfilmes.dtDesativa = GETDATE()');
    zGen.sql.add('where');
    zGen.sql.add('tblfilmes.idCadastro = :idFilme');
    zGen.parambyname('idFilme').asinteger := zConsultafilmes.fieldbyname('idCadastro').asinteger;
    zGen.ExecSQL;

  except
    on e :exception do
    begin
      raise exception.create('Falha ao atualizar registro : '+ e.Message);
    end

  end;

  showmessage('Registro desativado com sucesso');
  zConsultafilmes.Refresh;

end;

procedure TfrmCadastroFilmes.alimentaAtores;
begin
  tblAtores.DataController.RecordCount := 0;
  tblAtoresSelecionados.DataController.RecordCount := 0;
  zGen.sql.clear;
  zgen.sql.add('select');
  zgen.sql.add('a.idCadastro, ');
  zgen.sql.add('a.nome + char(32)+ a.sobrenome as nome');
  zgen.sql.add('from');
  zgen.sql.add('tblAtores a');
  zgen.sql.add(' where');
  zgen.sql.add(' a.ativo = 1');
  zgen.Open;
  zGen.First;
  while not zGen.Eof do
  begin

    tblAtores.DataController.RecordCount := tblAtores.DataController.RecordCount + 1;
    tblAtores.DataController.values[tblAtores.DataController.RecordCount-1,0]:= zGen.fieldbyname('idCadastro').asstring;
    tblAtores.DataController.values[tblAtores.DataController.RecordCount-1,1]:= zGen.fieldbyname('nome').asstring;

    zGen.Next;
  end;
end;

procedure TfrmCadastroFilmes.alimentadiretores;
begin

  tblDiretores.DataController.RecordCount := 0;
  tblDiretoresSelecionados.DataController.RecordCount := 0;
  zGen.sql.clear;
  zgen.sql.add('select');
  zgen.sql.add('a.idCadastro, ');
  zgen.sql.add('a.nome + char(32)+ a.sobrenome as nome');
  zgen.sql.add('from');
  zgen.sql.add('tbldiretores a');
  zgen.sql.add(' where');
  zgen.sql.add(' a.ativo = 1');
  zgen.Open;
  zGen.First;
  while not zGen.Eof do
  begin

    tblDiretores.DataController.RecordCount := tblDiretores.DataController.RecordCount + 1;
    tblDiretores.DataController.values[tblDiretores.DataController.RecordCount-1,0]:= zGen.fieldbyname('idCadastro').asstring;
    tblDiretores.DataController.values[tblDiretores.DataController.RecordCount-1,1]:= zGen.fieldbyname('nome').asstring;

    zGen.Next;
  end;
end;

procedure TfrmCadastroFilmes.alimentaGeneros;
begin

  tblGeneros.DataController.RecordCount := 0;
  tblGenerosSelecionados.DataController.RecordCount := 0;
  zGen.sql.clear;
  zgen.sql.add('select');
  zgen.sql.add('a.idCadastro, ');
  zgen.sql.add('a.genero');
  zgen.sql.add('from');
  zgen.sql.add('tblGeneros a');
  zgen.sql.add(' where');
  zgen.sql.add(' a.ativo = 1');
  zgen.Open;
  zGen.First;
  while not zGen.Eof do
  begin

    tblGeneros.DataController.RecordCount := tblGeneros.DataController.RecordCount + 1;
    tblGeneros.DataController.values[tblGeneros.DataController.RecordCount-1,0]:= zGen.fieldbyname('idCadastro').asstring;
    tblGeneros.DataController.values[tblGeneros.DataController.RecordCount-1,1]:= zGen.fieldbyname('genero').asstring;

    zGen.Next;
  end;
end;

procedure TfrmCadastroFilmes.FormCreate(Sender: TObject);
var i,x : integer;
begin

  i :=  0 ;

  while i < Self.ComponentCount do
  begin
    if Components[i] is TcxGridTableView then
    begin
      x:=0;
      while x < (Components[i] as TcxGridTableView).ColumnCount do
      begin
        (Components[i] as TcxGridTableView).Columns[x].DataBinding.ValueTypeClass := TcxStringValueType;
        inc(x);
      end;
    end;
    inc(i);
  end;

  {
  for i := tblAtores.DataController.RecordCount downto  0 do
  begin

  end;}



end;

procedure TfrmCadastroFilmes.tblAtoresDblClick(Sender: TObject);
begin

  if tblAtores.datacontroller.FocusedRecordIndex= -1 then
  begin
    exit;
  end;

  if tblAtores.datacontroller.RecordCount= 0 then
  begin
    exit;
  end;

  tblAtoresSelecionados.datacontroller.RecordCount := tblAtoresSelecionados.DataController.RecordCount + 1;
  tblAtoresSelecionados.DataController.Values[tblAtoresSelecionados.DataController.RecordCount-1,0] := tblAtores.datacontroller.Values[tblAtores.DataController.FocusedRecordIndex,0];
  tblAtoresSelecionados.DataController.Values[tblAtoresSelecionados.DataController.RecordCount-1,1] := tblAtores.datacontroller.Values[tblAtores.DataController.FocusedRecordIndex,1];
  tblAtores.DataController.DeleteFocused;

end;

procedure TfrmCadastroFilmes.edtAnoKeyPress(Sender: TObject;
  var Key: Char);
begin
  if not (key in ['0'..'9',#13,#10,#39,#8]) then
  begin
    key := #0;
  end;

end;

procedure TfrmCadastroFilmes.edtCodigoMundialKeyPress(Sender: TObject;
  var Key: Char);
begin
  if not (key in ['0'..'9',#13,#10,#39,#8]) then
  begin
    key := #0;
  end;
end;

procedure TfrmCadastroFilmes.edtTempoDuracaoKeyPress(Sender: TObject;
  var Key: Char);
begin
  if not (key in ['0'..'9',#13,#10,#39,#8]) then
  begin
    key := #0;
  end;
end;

procedure TfrmCadastroFilmes.tblAtoresSelecionadosDblClick(
  Sender: TObject);
begin
  if tblAtoresSelecionados.datacontroller.RecordCount= 0 then
  begin
    exit;
  end;

  if tblAtoresSelecionados.datacontroller.FocusedRecordIndex= -1 then
  begin
    exit;
  end;

  tblAtores.datacontroller.RecordCount := tblAtores.DataController.RecordCount + 1;
  tblAtores.DataController.Values[tblAtores.DataController.RecordCount-1,0] := tblAtoresSelecionados.datacontroller.Values[tblAtoresSelecionados.DataController.FocusedRecordIndex,0];
  tblAtores.DataController.Values[tblAtores.DataController.RecordCount-1,1] := tblAtoresSelecionados.datacontroller.Values[tblAtoresSelecionados.DataController.FocusedRecordIndex,1];
  tblAtoresSelecionados.DataController.DeleteFocused;
end;

procedure TfrmCadastroFilmes.tblDiretoresDblClick(Sender: TObject);
begin
  if tblDiretores.datacontroller.FocusedRecordIndex= -1 then
  begin
    exit;
  end;


  if tblDiretores.datacontroller.RecordCount= 0 then
  begin
    exit;
  end;

  tblDiretoresSelecionados.datacontroller.RecordCount := tblDiretoresSelecionados.DataController.RecordCount + 1;
  tblDiretoresSelecionados.DataController.Values[tblDiretoresSelecionados.DataController.RecordCount-1,0] := tblDiretores.datacontroller.Values[tblDiretores.DataController.FocusedRecordIndex,0];
  tblDiretoresSelecionados.DataController.Values[tblDiretoresSelecionados.DataController.RecordCount-1,1] := tblDiretores.datacontroller.Values[tblDiretores.DataController.FocusedRecordIndex,1];
  tblDiretores.DataController.DeleteFocused;
end;

procedure TfrmCadastroFilmes.tblDiretoresSelecionadosDblClick(
  Sender: TObject);
begin
  if tblDiretoresSelecionados.datacontroller.FocusedRecordIndex= -1 then
  begin
    exit;
  end;


  if tblDiretoresSelecionados.datacontroller.RecordCount= 0 then
  begin
    exit;
  end;

  tblDiretores.datacontroller.RecordCount := tblDiretores.DataController.RecordCount + 1;
  tblDiretores.DataController.Values[tblDiretores.DataController.RecordCount-1,0] := tblDiretoresSelecionados.datacontroller.Values[tblDiretoresSelecionados.DataController.FocusedRecordIndex,0];
  tblDiretores.DataController.Values[tblDiretores.DataController.RecordCount-1,1] := tblDiretoresSelecionados.datacontroller.Values[tblDiretoresSelecionados.DataController.FocusedRecordIndex,1];
  tblDiretoresSelecionados.DataController.DeleteFocused;
end;

procedure TfrmCadastroFilmes.tblGenerosDblClick(Sender: TObject);
begin

  if tblGeneros.datacontroller.FocusedRecordIndex= -1 then
  begin
    exit;
  end;


  if tblGeneros.datacontroller.RecordCount= 0 then
  begin
    exit;
  end;

  tblGenerosSelecionados.datacontroller.RecordCount := tblGenerosSelecionados.DataController.RecordCount + 1;
  tblGenerosSelecionados.DataController.Values[tblGenerosSelecionados.DataController.RecordCount-1,0] := tblGeneros.datacontroller.Values[tblGeneros.DataController.FocusedRecordIndex,0];
  tblGenerosSelecionados.DataController.Values[tblGenerosSelecionados.DataController.RecordCount-1,1] := tblGeneros.datacontroller.Values[tblGeneros.DataController.FocusedRecordIndex,1];
  tblGeneros.DataController.DeleteFocused;
end;

procedure TfrmCadastroFilmes.tblGenerosSelecionadosDblClick(
  Sender: TObject);
begin

  if tblGenerosSelecionados.datacontroller.FocusedRecordIndex= -1 then
  begin
    exit;
  end;


  if tblGenerosSelecionados.datacontroller.RecordCount= 0 then
  begin
    exit;
  end;

  tblGeneros.datacontroller.RecordCount := tblGeneros.DataController.RecordCount + 1;
  tblGeneros.DataController.Values[tblGeneros.DataController.RecordCount-1,0] := tblGenerosSelecionados.datacontroller.Values[tblGenerosSelecionados.DataController.FocusedRecordIndex,0];
  tblGeneros.DataController.Values[tblGeneros.DataController.RecordCount-1,1] := tblGenerosSelecionados.datacontroller.Values[tblGenerosSelecionados.DataController.FocusedRecordIndex,1];
  tblGenerosSelecionados.DataController.DeleteFocused;
end;

procedure TfrmCadastroFilmes.begintransaction;
begin

  try
    zGen.sql.clear;
    zgen.sql.add('BEGIN TRANSACTION');
    zGen.ExecSQL

  except
    on e : exception do
    begin
      raise exception.create('Falha ao inicializar a transação : '+ e.message);
    end;

  end;

end;

procedure TfrmCadastroFilmes.committransaction;
begin


  try
    zGen.sql.clear;
    zgen.sql.add('COMMIT TRANSACTION');
    zGen.ExecSQL

  except
    on e : exception do
    begin
      rollbacktransaction(e.Message);
      raise exception.create('Falha ao realizar commit da transação : '+ e.message);
    end;

  end;


end;

procedure TfrmCadastroFilmes.rollbacktransaction(msg:string);
begin

  try
    zGen.sql.clear;
    zgen.sql.add('ROLLBACK TRANSACTION');
    zGen.ExecSQL

  except
    on e : exception do
    begin
      raise exception.create('Falha ao realizar rollback da transação : '+ e.message + ' , mensagem original : '+ msg);
    end;

  end;

end;

procedure TfrmCadastroFilmes.clear;
begin
  edtNome.Clear;
  edtAno.Clear;
  edtCodigoMundial.Clear;
  memoSinopse.Clear;
  edtTempoDuracao.Clear;
  alimentaAtores;
  alimentadiretores;
  alimentaGeneros;
  PageControl1.TabIndex := 0;
end;

function TfrmCadastroFilmes.insertFilme: integer;
begin

  result := -1;

  try
    zGen.SQL.clear;
    zGen.SQL.add(' INSERT INTO tblFilmes');
    zGen.SQL.add('(');
    zGen.SQL.add('  ano,');
    zGen.SQL.add('  nome,');
    zGen.SQL.add('  sinopse,');
    zGen.SQL.add('  codigoMundial,');
    zGen.SQL.add('  tempoduracao');
    zGen.SQL.add(') ');
    zGen.SQL.add('VALUES (');
    zGen.SQL.add('  :ano,');
    zGen.SQL.add('  :nome,');
    zGen.SQL.add('  :sinopse,');
    zGen.SQL.add('  :codigoMundial,');
    zGen.SQL.add('  :tempoduracao');
    zGen.SQL.add(')');
    zgen.sql.add('select IDENT_CURRENT('+quotedstr('tblFilmes')+') as idFilme');
    zgen.parambyname('ano').asstring :=trim(edtAno.Text);
    zgen.parambyname('nome').asstring :=trim(edtNome.Text);
    zgen.parambyname('sinopse').AsString :=trim(memoSinopse.Text);
    zgen.parambyname('codigoMundial').asstring :=trim(edtCodigoMundial.Text);
    zgen.parambyname('tempoduracao').asstring :=trim(edtTempoDuracao.Text);
    zGen.Open;

    result := zGen.fieldbyname('idfilme').asinteger;

  except
    on e : exception do
    begin
      raise exception.create('Falha ao inserir filme : '+ e.Message);
    end;

  end;


end;

procedure TfrmCadastroFilmes.insertVincFilmeAtor(idFilme: integer);
var i : integer;
begin


  i := 0;
  while i < tblAtoresSelecionados.DataController.RecordCount do
  begin
    try
      zGen.SQL.clear;
      zGen.SQL.add('INSERT INTO tblvincFilmeAtor');
      zGen.SQL.add('(');
      zGen.SQL.add('  idFilme,');
      zGen.SQL.add('  idAtor');
      zGen.SQL.add(') ');
      zGen.SQL.add('VALUES (');
      zGen.SQL.add('  :idFilme,');
      zGen.SQL.add('  :idAtor');
      zGen.SQL.add(')');
      zgen.parambyname('idFilme').asinteger := idFilme;
      zgen.parambyname('idAtor').asstring :=tblAtoresSelecionados.datacontroller.values[i,0];
      zGen.ExecSQL;

    except
      on e : exception do
      begin
        raise exception.create('Falha ao inserir ator : '+ e.Message);
      end;

    end;

    inc(i);
  end;




end;

procedure TfrmCadastroFilmes.insertVincFilmeDiretor(idFilme: integer);
var i : integer;
begin

  i := 0;
  while i < tblDiretoresSelecionados.DataController.RecordCount do
  begin
    try
      zGen.SQL.clear;
      zGen.SQL.add('INSERT INTO tblvincFilmeDiretor');
      zGen.SQL.add('(');
      zGen.SQL.add('  idFilme,');
      zGen.SQL.add('  idDiretor');
      zGen.SQL.add(') ');
      zGen.SQL.add('VALUES (');
      zGen.SQL.add('  :idFilme,');
      zGen.SQL.add('  :idDiretor');
      zGen.SQL.add(')');
      zgen.parambyname('idFilme').asinteger := idFilme;
      zgen.parambyname('idDiretor').asstring :=tblDiretoresSelecionados.datacontroller.values[i,0];
      zGen.ExecSQL;

    except
      on e : exception do
      begin
        raise exception.create('Falha ao inserir diretor : '+ e.Message);
      end;

    end;

    inc(i);
  end;



end;

procedure TfrmCadastroFilmes.insertVincFilmeGenero(idFilme: integer);
var i : integer;
begin

  i := 0;
  while i < tblGenerosSelecionados.DataController.RecordCount do
  begin
    try
      zGen.SQL.clear;
      zGen.SQL.add('INSERT INTO tblVincFilmeGenero');
      zGen.SQL.add('(');
      zGen.SQL.add('  idFilme,');
      zGen.SQL.add('  idgenero');
      zGen.SQL.add(') ');
      zGen.SQL.add('VALUES (');
      zGen.SQL.add('  :idFilme,');
      zGen.SQL.add('  :idgenero');
      zGen.SQL.add(')');
      zgen.parambyname('idFilme').asinteger := idFilme;
      zgen.parambyname('idgenero').asstring :=tblGenerosSelecionados.datacontroller.values[i,0];
      zGen.ExecSQL;

    except
      on e : exception do
      begin
        raise exception.create('Falha ao inserir genero : '+ e.Message);
      end;

    end;

    inc(i);
  end;

end;

procedure TfrmCadastroFilmes.alimentaFilmes;
begin
  zConsultafilmes.sql.Clear;
  zConsultafilmes.SQL.add('select');
  zConsultafilmes.SQL.add('a.idCadastro, ');
  zConsultafilmes.SQL.add('a.nome, ');
  zConsultafilmes.SQL.add('a.codigoMundial,');
  zConsultafilmes.SQL.add('a.ano');
  zConsultafilmes.SQL.add('from');
  zConsultafilmes.SQL.add('tblFilmes a');
  zConsultafilmes.SQL.add('where');
  zConsultafilmes.SQL.add('a.ativo = 1');
  zConsultafilmes.Open;

end;

procedure TfrmCadastroFilmes.aba2Execute(Sender: TObject);
begin
  PageControl1.TabIndex := 1;  
end;

procedure TfrmCadastroFilmes.tblAtoresKeyPress(Sender: TObject;
  var Key: Char);
begin
  if Key = #13 then
  begin
    tblAtoresDblClick(nil);  
  end;

end;

procedure TfrmCadastroFilmes.Action1Execute(Sender: TObject);
begin
  cxGrid2.SetFocus;
end;

procedure TfrmCadastroFilmes.Action2Execute(Sender: TObject);
begin

  cxGrid3.SetFocus;
end;

procedure TfrmCadastroFilmes.tblAtoresSelecionadosKeyPress(Sender: TObject;
  var Key: Char);
begin
  if key = #13 then
  begin
    tblAtoresSelecionadosDblClick(nil);    
  end;

end;

end.
