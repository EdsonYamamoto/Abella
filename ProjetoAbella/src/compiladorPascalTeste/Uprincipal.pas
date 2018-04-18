unit Uprincipal;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Menus, RpCon, RpConDS, DB, ZAbstractRODataset, ZAbstractDataset,
  ZDataset, RpDefine, RpRave;

type
  TfrmPrincipal = class(TForm)
    MainMenu1: TMainMenu;
    Cadastros1: TMenuItem;
    Relatrios1: TMenuItem;
    Locao1: TMenuItem;
    Sair1: TMenuItem;
    Atores1: TMenuItem;
    Diretores1: TMenuItem;
    Genero1: TMenuItem;
    Nacionalidades1: TMenuItem;
    Filmes1: TMenuItem;
    Filmes2: TMenuItem;
    RvProject1: TRvProject;
    ZQuery1: TZQuery;
    RvDataSetConnection1: TRvDataSetConnection;
    procedure Sair1Click(Sender: TObject);
    procedure Atores1Click(Sender: TObject);
    procedure Diretores1Click(Sender: TObject);
    procedure Genero1Click(Sender: TObject);
    procedure Nacionalidades1Click(Sender: TObject);
    procedure Filmes1Click(Sender: TObject);
    procedure Filmes2Click(Sender: TObject);
    procedure Locao1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  frmPrincipal: TfrmPrincipal;

implementation

uses UCadastroAtores, UcadastroDiretores, UcadastroGeneros,
  UCadastroNacionalidades, UCadastroFilmes, UDataModule, ULocacao;

{$R *.dfm}

procedure TfrmPrincipal.Sair1Click(Sender: TObject);
begin
  application.terminate;
end;

procedure TfrmPrincipal.Atores1Click(Sender: TObject);
begin

  if frmCadastroAtores = nil then
  begin
    Application.CreateForm(TfrmCadastroAtores, frmCadastroAtores);
  end;
  frmCadastroAtores.ShowModal;



end;

procedure TfrmPrincipal.Diretores1Click(Sender: TObject);
begin

  if frmCadastroDiretores = nil then
  begin
    Application.CreateForm(TfrmCadastroDiretores, frmCadastroDiretores);
  end;
  frmCadastroDiretores.ShowModal;

end;

procedure TfrmPrincipal.Genero1Click(Sender: TObject);
begin

  if frmCadastroGeneros = nil then
  begin
    Application.CreateForm(TfrmCadastroGeneros, frmCadastroGeneros);
  end;
  frmCadastroGeneros.ShowModal;


end;

procedure TfrmPrincipal.Nacionalidades1Click(Sender: TObject);
begin


  if frmCadastroNacionalidades = nil then
  begin
    Application.CreateForm(TfrmCadastroNacionalidades, frmCadastroNacionalidades);
  end;
  frmCadastroNacionalidades.ShowModal;
end;

procedure TfrmPrincipal.Filmes1Click(Sender: TObject);
begin

  if frmCadastroFilmes = nil then
  begin
    Application.CreateForm(TfrmCadastroFilmes, frmCadastroFilmes);
  end;
  frmCadastroFilmes.ShowModal;

end;

procedure TfrmPrincipal.Filmes2Click(Sender: TObject);
begin

  ZQuery1.sql.clear;
  ZQuery1.SQL.add('select');
  ZQuery1.SQL.add('a.nome,');
  ZQuery1.SQL.add('a.ano,');
  ZQuery1.SQL.add('a.tempoduracao');
  ZQuery1.SQL.add('from');
  ZQuery1.SQL.add('tblFilmes a');
  ZQuery1.SQL.add('where');
  ZQuery1.SQL.add('a.ativo= 1');
  ZQuery1.Open;

  RvProject1.Execute;



end;

procedure TfrmPrincipal.Locao1Click(Sender: TObject);
begin
 if frmLocacao=nil then
 begin
  
 end;

end;

end.
