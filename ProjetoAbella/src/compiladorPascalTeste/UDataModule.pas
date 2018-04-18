unit UDataModule;

interface

uses
  SysUtils, Classes, ZConnection;

type
  Tdatamodulelocadora = class(TDataModule)
    zConLocadora: TZConnection;
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  datamodulelocadora: Tdatamodulelocadora;

implementation

{$R *.dfm}

end.
