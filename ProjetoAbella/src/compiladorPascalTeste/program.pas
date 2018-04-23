// Programa que cálcula o salário de funcionários
Program CalcularSalario;
	Var TempoEmAnos : Integer; 
	Var ValorSalario: Real; 
	Begin 
		
	if (Edit1.Text = 'nebrio') then 
	ShowMessage('Nome Lind') else 
	ShowMessage('Nome Feio'); 	
		
		
	if (Edit1.Text = 'nebrio') then 
	begin 
	ShowMessage('Nome Lindo'); 
	ShowMessage('Nome de Gente Inteligente'); 
	end else 
	begin 
	ShowMessage('Nome Feio'); 
	ShowMessage('Nome de gente Menos Inteligente'); 
	end; 


        sql.add('askndlkas'+quotedstring('batata'));
        SQL.ADD('askndlkas'+quotedstring('banana'));
End;


procedure TfrmCadastroDiretores.btnSalvarClick(Sender: TObject);
begin

  if trim(edtnome.text) = emptystr then
  begin
    edtnome.setfocus;
    raise exception.create('O nome é obrigatório');
  end;
	else if trim(edtnome.text) = 'formula' then
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
    zGen.sql.add('INSERT INTO tblDiretores'); zGen.sql.add('  idNacionalidade'); zGen.sql.add('  idNacionalidade'); zGen.sql.add('  idNacionalidade');
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