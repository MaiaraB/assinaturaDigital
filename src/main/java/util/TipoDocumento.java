package util;


public enum TipoDocumento implements ITipoDocumento{
	
	ENCAMINHAMENTO			(1,"Encaminhamento",				        "ENC",		  new String[] {"encaminhamento"}),
    FAX						(2,"Fax",							        "FAX",		  new String[] {"fax"}),
    MEMORANDO				(4,"Memorando",						        "MEM",		  new String[] {"memorando"}),
    MDO						(5,"MDO",							        "MDO",		  new String[] {"mdo"}),
    NOTA_BI					(6,"Nota para Boletim",				        "NBI",		  new String[] {"nota"}),
    OFICIO_RESTRITO			(7,"Ofício Restrito",				        "OF",		  new String[] {"oficio","restrito"}),
    OFICIO_PARTICULAR		(8,"Ofício Particular",				        "OF",		  new String[] {"oficio","particular"}),
    OUTRO					(9,"Outro",							        "OUTRO",	  new String[] {"outro"}),
    PARTE					(10,"Parte",						        "PARTE",	  new String[] {"parte"}),
    RADIOGRAMA				(11,"Radiograma",					        "RD",		  new String[] {"radiograma"}),
    RELATORIO				(12,"Relatório",					        "RELAT",	  new String[] {"relatorio"}),
    REMESSA					(13,"Remessa",						        "RMSA",		  new String[] {"remessa"}),
    REQUERIMENTO			(14,"Requerimento",					        "REQ",		  new String[] {"requerimento"}),
    REQUERIMENTO_INFO		(15,"Informação de Requerimento",	        "REQINF",	  new String[] {"requerimento","info"}),
    RESTITUICAO				(16,"Restituição",					        "REST",		  new String[] {"restituicao"}),
    PEDIDO_MATERIAL			(17,"Pedido de Material",			        "PDDMAT",	  new String[] {"pedido","material"}),
    PEDIDO_VIATURA			(18,"Pedido de Viatura",			        "PDDVTR",	  new String[] {"pedido","viatura"}),
    AUTORIZACAO_RADIOGRAMA	(19,"Autorização de Radiogramas",	        "AUTRAD",	  new String[] {"autorizacao","radiograma"}),
    DESCARGA_MATERIAL		(20,"Descarga de Material",			        "DESCMAT",    new String[] {"descarga","material"}),
    PEDIDO_APOIO_EVENTO		(21,"Pedido de Apoio a Evento",		        "PDDAPEVT",   new String[] {"pedido","apoio","evento"}),
    PEDIDO_PASSAGEM_DIARIA  (22,"Pedido de Passagens e Diárias",        "PDDPASSDIA", new String[] {"pedido","passagem","diaria"}),
	
    
    
	//Documentos com numeração maior que 60 são dos documentos novos (IG 10-42 de 2011)
    DIEX 					(60,"DIEx",                         "DIEX",     new String[] {"diex"}),
	DIEX_SIMPLES  			(61,"DIEx Simplificado",			"DIEXSMP",	new String[] {"diex","simples"}),
	OFICIO					(62,"Ofício",                       "OFICIO",   new String[] {"oficio"}),
	NOTA_PARA_BOLETIM	    (63,"Nota para Boletim",            "NB",       new String[] {"nota","para","boletim"}), 
	INFORMACAO_REQUERIMENTO (64,"Informação de Requerimento",   "INFOREQ",  new String[] {"informacao","requerimento"}),
	REQUERIMENTO_IG2011		(65,"Requerimento",   				"REQ2011", 	new String[] {"requerimento2011"}),
    

	//Documentos com numeração maior que 80 são dos documentos que aparecem na tela do Protocolista
	BOLETIM					(80,"Boletim",                       "BI",   		new String[] {"boletim"}),
	CALENDARIO				(81,"Calendário",                    "CALEND",   	new String[] {"calendario"}),
	CARTA					(82,"Carta",                      	 "CART",   		new String[] {"carta"}),
	ESTUDO					(83,"Estudo",                 	     "ESTU",   		new String[] {"estudo"}),
	GUIA_RECOLHIMENTO		(84,"Guia de Recolhimento",          "GUIRECO",   	new String[] {"guia","recolhimento"}),
	INFORMACAO				(85,"Informação",                    "INFO",   		new String[] {"informacao"}),
	MEMORIA					(86,"Memória",       	             "MEMO",   		new String[] {"memoria"}),
	MENSAGEM				(87,"Mensagem",       	             "MSG",   		new String[] {"mensagem"}),
	MENSAGEM_SIAFI			(88,"Mensagem SIAFI",  	             "MSGSIA", 		new String[] {"mensagem","siafi"}),
	NOTA 					(89,"Nota",			  	             "NOTA", 		new String[] {"nota"}),
	NOTA_INFORMATIVA		(90,"Nota Informativa",	             "NOTAINFO",	new String[] {"nota","informativa"}),
	ORDEM_FORNECIMENTO		(91,"Ordem de Fornecimento",	     "ORDFOR",		new String[] {"ordem","fornecimento"}),
	ORDEM_INSTRUCAO			(92,"Ordem de Instrução",	   		 "ORDINS",		new String[] {"ordem","instrucao"}),
	ORDEM_RECOLHIMENTO		(93,"Ordem de Recolhimento",		 "ORDREC",		new String[] {"ordem","recolhimento"}),
	ORDEM_SERVICO			(94,"Ordem de Serviço",	    		 "ORDSERV",		new String[] {"ordem","servico"}),
	ORDEM_TRANSFERENCIA		(95,"Ordem de Transferência",	     "ORDTANS",		new String[] {"ordem","transferencia"}),
	PARECER					(96,"Parecer",	     				 "PRECR",		new String[] {"parecer"}),
	PORTARIA				(97,"Portaria",					     "PORT",		new String[] {"portaria"}),
	PROCESSO				(98,"Processo",	    				 "PROCSS",		new String[] {"processo"}),
	REQUISICAO				(99,"Requisição",				     "REQAO",		new String[] {"requisicao"}),
    
    
    
    
    
    //Documentos com numeração maior que a do  EXT será sempre documento externo
    EXT						(30,"Externo","EXT",	new String[] {"externo"}),
    ENCAMINHAMENTO_EXT		(31,ENCAMINHAMENTO,		new String[] {"externo"}),
    FAX_EXT					(32,FAX,				new String[] {"externo"}),
    MEMORANDO_EXT			(34,MEMORANDO,			new String[] {"externo"}),
    MDO_EXT					(35,MDO,				new String[] {"externo"}),					
    NOTA_BI_EXT				(36,NOTA_BI,			new String[] {"externo"}),
    OFICIO_RESTRITO_EXT		(37,OFICIO_RESTRITO,	new String[] {"externo"}),
    OFICIO_PARTICULAR_EXT	(38,OFICIO_PARTICULAR,	new String[] {"externo"}),
    OUTRO_EXT				(39,OUTRO,				new String[] {"externo"}),
    PARTE_EXT				(40,PARTE,				new String[] {"externo"}),
    RADIOGRAMA_EXT			(41,RADIOGRAMA,			new String[] {"externo"}),
    RELATORIO_EXT			(42,RELATORIO,			new String[] {"externo"}),
    REMESSA_EXT				(43,REMESSA,			new String[] {"externo"}),
    REQUERIMENTO_EXT		(44,REQUERIMENTO,		new String[] {"externo"}),
    REQUERIMENTO_INFO_EXT	(45,REQUERIMENTO_INFO,	new String[] {"externo"}),
    RESTITUICAO_EXT			(46,RESTITUICAO,		new String[] {"externo"}),
    DIEX_EXT				(47,DIEX,				new String[] {"externo"}),
	DIEX_SIMPLES_EXT		(48,DIEX_SIMPLES,		new String[] {"externo"}),
	OFICIO_EXT				(49,OFICIO,           	new String[] {"externo"});
	
	
	private int cod;
	private String nome;
	private String nomeAbrv;
	private String[] nomeArray;
	
	
	TipoDocumento(int cod, String nome, String nomeAbr, String[] nomeArray){
		this.cod = cod;
		this.nomeAbrv = nomeAbr;
		this.nome = nome;
		this.nomeArray = nomeArray;
	}

	//Construtor para os documentos externos
	TipoDocumento(int cod, ITipoDocumento tipodoc , String[] nomeArray){
		this(cod, tipodoc.getNome(), tipodoc.getNomeAbr(),nomeArray);
	}
		
	public int getCodigo() {
		return cod;
	}

	public String getNome() {
		return nome;
	}

	public String getNomeAbr() {
		return nomeAbrv;
	}
	
	public String[] getNomeArray() {
		return nomeArray;
	}
	
	public static String getNomeDocumento(int codigo){
		for (TipoDocumento tipo : TipoDocumento.values()){
			if (tipo.getCodigo() == codigo) return tipo.getNome();
		}
		return "?";
	}
	
	public String getNomeModificado(){
		String nomeModificado = getNome().toUpperCase();
		
		if(getCodigo() == 7){
			nomeModificado= "OFÍCIO (Interno à Força)";
			
			
		}else if(getCodigo() == 8){
				nomeModificado = "OFÍCIO (Externo à Força)";
		}			
		return nomeModificado;
	}
	
	public static TipoDocumento getDefault(){
		return TipoDocumento.EXT;
	}

	public static TipoDocumento getInstance(String nomeAbr) {
		for(TipoDocumento t : TipoDocumento.values()){
			if(t.getNomeAbr().equals(nomeAbr)){
				return t;
			}
		}
		
		for(TipoDocumento t : TipoDocumento.values()){
			if(t.getNome().equals(nomeAbr)){
				return t;
			}
		}
		
		return null;
	}

	public String getAction() {
		return null;
	}
}
