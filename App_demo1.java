
    

// app_index.java
// Este é o arquivo principal da aplicação.
// Não é ideal colocar todo o código aqui, mas para fins de demonstração
// e seguindo as instruções de "java pura", será feito desta forma.

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ----------------------------------------------------------------------
// 1. Entidades (Classes Pojo)
// ----------------------------------------------------------------------

/**
 * Representa um tipo de artigo (ex: Eletrônico, Vestuário, Alimento).
 */
class TipoArtigo {
    private int id;
    private String nome;

    public TipoArtigo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    @Override
    public String toString() {
        return "TipoArtigo{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               '}';
    }
}

/**
 * Representa uma pergunta que pode ser associada a um artigo ou checklist.
 */
class Pergunta {
    private int id;
    private String texto;

    public Pergunta(int id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    public int getId() { return id; }
    public String getTexto() { return texto; }

    public void setTexto(String texto) { this.texto = texto; }

    @Override
    public String toString() {
        return "Pergunta{" +
               "id=" + id +
               ", texto='" + texto + '\'' +
               '}';
    }
}

/**
 * Representa uma resposta a uma pergunta.
 */
class Resposta {
    private int id;
    private Pergunta pergunta;
    private String texto;

    public Resposta(int id, Pergunta pergunta, String texto) {
        this.id = id;
        this.pergunta = pergunta;
        this.texto = texto;
    }

    public int getId() { return id; }
    public Pergunta getPergunta() { return pergunta; }
    public String getTexto() { return texto; }

    public void setTexto(String texto) { this.texto = texto; }

    @Override
    public String toString() {
        return "Resposta{" +
               "id=" + id +
               ", pergunta=" + (pergunta != null ? pergunta.getTexto() : "N/A") +
               ", texto='" + texto + '\'' +
               '}';
    }
}

/**
 * Representa um Artigo (produto) no sistema.
 * Contém informações básicas e pode ter uma lista de respostas a perguntas.
 */
class Artigo {
    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private TipoArtigo tipo;
    private List<Resposta> respostas; // Respostas específicas para este artigo

    public Artigo(int id, String nome, String descricao, double preco, TipoArtigo tipo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.tipo = tipo;
        this.respostas = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }
    public TipoArtigo getTipo() { return tipo; }
    public List<Resposta> getRespostas() { return respostas; }

    public void addResposta(Resposta resposta) {
        this.respostas.add(resposta);
    }

    @Override
    public String toString() {
        return "Artigo{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", descricao='" + descricao + '\'' +
               ", preco=" + preco +
               ", tipo=" + (tipo != null ? tipo.getNome() : "N/A") +
               ", respostas=" + respostas.size() + " itens" +
               '}';
    }
}

/**
 * Representa uma Checklist, que pode ser associada a um tipo de artigo
 * e conter perguntas específicas para aquele tipo.
 */
class Checklist {
    private int id;
    private String nome;
    private TipoArtigo tipoArtigo;
    private List<Pergunta> perguntas;

    public Checklist(int id, String nome, TipoArtigo tipoArtigo) {
        this.id = id;
        this.nome = nome;
        this.tipoArtigo = tipoArtigo;
        this.perguntas = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public TipoArtigo getTipoArtigo() { return tipoArtigo; }
    public List<Pergunta> getPerguntas() { return perguntas; }

    public void addPergunta(Pergunta pergunta) {
        this.perguntas.add(pergunta);
    }

    @Override
    public String toString() {
        return "Checklist{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", tipoArtigo=" + (tipoArtigo != null ? tipoArtigo.getNome() : "N/A") +
               ", perguntas=" + perguntas.size() + " itens" +
               '}';
    }
}

// ----------------------------------------------------------------------
// 2. Repositório (Simulação de Banco de Dados em memória)
// ----------------------------------------------------------------------

class Repositorio {
    private List<TipoArtigo> tiposArtigo;
    private List<Pergunta> perguntas;
    private List<Resposta> respostas;
    private List<Artigo> artigos;
    private List<Checklist> checklists;

    private int nextTipoArtigoId = 1;
    private int nextPerguntaId = 1;
    private int nextRespostaId = 1;
    private int nextArtigoId = 1;
    private int nextChecklistId = 1;

    public Repositorio() {
        this.tiposArtigo = new ArrayList<>();
        this.perguntas = new ArrayList<>();
        this.respostas = new ArrayList<>();
        this.artigos = new ArrayList<>();
        this.checklists = new ArrayList<>();
        inicializarDados(); // Carrega alguns dados de exemplo
    }

    private void inicializarDados() {
        // Tipos de Artigo
        TipoArtigo tipoEletronico = addTipoArtigo(new TipoArtigo(nextTipoArtigoId++, "Eletrônico"));
        TipoArtigo tipoVestuario = addTipoArtigo(new TipoArtigo(nextTipoArtigoId++, "Vestuário"));
        TipoArtigo tipoLivro = addTipoArtigo(new TipoArtigo(nextTipoArtigoId++, "Livro"));

        // Perguntas
        Pergunta p1 = addPergunta(new Pergunta(nextPerguntaId++, "Qual a voltagem?"));
        Pergunta p2 = addPergunta(new Pergunta(nextPerguntaId++, "Tem garantia?"));
        Pergunta p3 = addPergunta(new Pergunta(nextPerguntaId++, "Qual o tamanho da tela?"));
        Pergunta p4 = addPergunta(new Pergunta(nextPerguntaId++, "Qual o material?"));
        Pergunta p5 = addPergunta(new Pergunta(nextPerguntaId++, "Qual o autor?"));
        Pergunta p6 = addPergunta(new Pergunta(nextPerguntaId++, "Qual o ano de publicação?"));


        // Artigos (Produtos)
        Artigo tv = addArtigo(new Artigo(nextArtigoId++, "Smart TV 50'", "TV de alta resolução", 2500.00, tipoEletronico));
        tv.addResposta(new Resposta(nextRespostaId++, p1, "Bivolt"));
        tv.addResposta(new Resposta(nextRespostaId++, p2, "Sim, 1 ano."));
        tv.addResposta(new Resposta(nextRespostaId++, p3, "50 polegadas"));

        Artigo geladeira = addArtigo(new Artigo(nextArtigoId++, "Geladeira Frost Free", "Geladeira duplex com freezer", 3200.00, tipoEletronico));
        geladeira.addResposta(new Resposta(nextRespostaId++, p1, "220V"));
        geladeira.addResposta(new Resposta(nextRespostaId++, p2, "Sim, 2 anos."));

        Artigo camisa = addArtigo(new Artigo(nextArtigoId++, "Camisa Social", "Camisa 100% algodão", 120.00, tipoVestuario));
        camisa.addResposta(new Resposta(nextRespostaId++, p4, "Algodão"));

        Artigo livroJava = addArtigo(new Artigo(nextArtigoId++, "Java: Como Programar", "Livro didático de Java", 180.00, tipoLivro));
        livroJava.addResposta(new Resposta(nextRespostaId++, p5, "Deitel & Deitel"));
        livroJava.addResposta(new Resposta(nextRespostaId++, p6, "2019"));

        // Checklists
        Checklist clEletronico = addChecklist(new Checklist(nextChecklistId++, "Checklist Eletrônicos", tipoEletronico));
        clEletronico.addPergunta(p1);
        clEletronico.addPergunta(p2);
        clEletronico.addPergunta(p3);

        Checklist clVestuario = addChecklist(new Checklist(nextChecklistId++, "Checklist Vestuário", tipoVestuario));
        clVestuario.addPergunta(p4);
    }

    // Métodos para TipoArtigo
    public TipoArtigo addTipoArtigo(TipoArtigo tipo) {
        tiposArtigo.add(tipo);
        return tipo;
    }
    public List<TipoArtigo> getAllTiposArtigo() { return new ArrayList<>(tiposArtigo); }
    public TipoArtigo getTipoArtigoById(int id) {
        for (TipoArtigo t : tiposArtigo) {
            if (t.getId() == id) return t;
        }
        return null;
    }
    public TipoArtigo getTipoArtigoByNome(String nome) {
        for (TipoArtigo t : tiposArtigo) {
            if (t.getNome().equalsIgnoreCase(nome)) return t;
        }
        return null;
    }

    // Métodos para Pergunta
    public Pergunta addPergunta(Pergunta pergunta) {
        perguntas.add(pergunta);
        return pergunta;
    }
    public List<Pergunta> getAllPerguntas() { return new ArrayList<>(perguntas); }
    public Pergunta getPerguntaById(int id) {
        for (Pergunta p : perguntas) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    // Métodos para Resposta
    public Resposta addResposta(Resposta resposta) {
        respostas.add(resposta);
        return resposta;
    }

    // Métodos para Artigo
    public Artigo addArtigo(Artigo artigo) {
        artigos.add(artigo);
        return artigo;
    }
    public List<Artigo> getAllArtigos() { return new ArrayList<>(artigos); }
    public Artigo getArtigoById(int id) {
        for (Artigo a : artigos) {
            if (a.getId() == id) return a;
        }
        return null;
    }

    // Métodos para Checklist
    public Checklist addChecklist(Checklist checklist) {
        checklists.add(checklist);
        return checklist;
    }
    public List<Checklist> getAllChecklists() { return new ArrayList<>(checklists); }

    // Métodos para gerar IDs sequenciais
    public int getNextTipoArtigoId() { return nextTipoArtigoId++; }
    public int getNextPerguntaId() { return nextPerguntaId++; }
    public int getNextRespostaId() { return nextRespostaId++; }
    public int getNextArtigoId() { return nextArtigoId++; }
    public int getNextChecklistId() { return nextChecklistId++; }
}

// ----------------------------------------------------------------------
// 3. Serviço/Gerenciador (Lógica de Negócio)
// ----------------------------------------------------------------------

class GerenciadorArtigos {
    private Repositorio repositorio;
    private Scanner scanner;

    public GerenciadorArtigos(Repositorio repositorio, Scanner scanner) {
        this.repositorio = repositorio;
        this.scanner = scanner;
    }

    // ------------------------------------------------------------------
    // Funcionalidades Requeridas
    // ------------------------------------------------------------------

    // 1. Mostrar Produtos
    public void mostrarProdutos() {
        List<Artigo> artigos = repositorio.getAllArtigos();
        if (artigos.isEmpty()) {
            System.out.println("Nenhum artigo cadastrado.");
            return;
        }
        System.out.println("\n--- Lista de Artigos (Produtos) ---");
        for (Artigo artigo : artigos) {
            System.out.println("ID: " + artigo.getId() +
                               ", Nome: " + artigo.getNome() +
                               ", Preço: " + String.format("%.2f", artigo.getPreco()) +
                               ", Tipo: " + artigo.getTipo().getNome());
            if (!artigo.getRespostas().isEmpty()) {
                System.out.println("  Detalhes:");
                for (Resposta resp : artigo.getRespostas()) {
                    System.out.println("    - " + resp.getPergunta().getTexto() + ": " + resp.getTexto());
                }
            }
        }
        System.out.println("------------------------------------");
    }

    // 2. Pesquisar produtos por 3 questões que vêm do usuário
    public void pesquisarProdutosPorQuestoes() {
        System.out.println("\n--- Pesquisar Artigos por Questões ---");
        List<String> termosBusca = new ArrayList<>();
        System.out.println("Por favor, digite até 3 termos de busca para as respostas dos artigos:");
        for (int i = 0; i < 3; i++) {
            System.out.print("Termo " + (i + 1) + " (ou Enter para pular): ");
            String termo = scanner.nextLine().trim();
            if (!termo.isEmpty()) {
                termosBusca.add(termo.toLowerCase());
            }
        }

        if (termosBusca.isEmpty()) {
            System.out.println("Nenhum termo de busca fornecido. Retornando ao menu.");
            return;
        }

        List<Artigo> resultados = new ArrayList<>();
        for (Artigo artigo : repositorio.getAllArtigos()) {
            boolean todasQuestoesCombinam = true;
            for (String termoBusca : termosBusca) {
                boolean termoEncontradoNoArtigo = false;
                // Busca nos detalhes do artigo (nome, descrição)
                if (artigo.getNome().toLowerCase().contains(termoBusca) ||
                    artigo.getDescricao().toLowerCase().contains(termoBusca) ||
                    artigo.getTipo().getNome().toLowerCase().contains(termoBusca)) {
                    termoEncontradoNoArtigo = true;
                }
                // Busca nas respostas associadas ao artigo
                for (Resposta resp : artigo.getRespostas()) {
                    if (resp.getTexto().toLowerCase().contains(termoBusca) ||
                        resp.getPergunta().getTexto().toLowerCase().contains(termoBusca)) {
                        termoEncontradoNoArtigo = true;
                        break;
                    }
                }
                if (!termoEncontradoNoArtigo) {
                    todasQuestoesCombinam = false;
                    break;
                }
            }
            if (todasQuestoesCombinam) {
                resultados.add(artigo);
            }
        }

        if (resultados.isEmpty()) {
            System.out.println("Nenhum artigo encontrado com todos os termos de busca fornecidos.");
        } else {
            System.out.println("\n--- Artigos Encontrados ---");
            for (Artigo artigo : resultados) {
                System.out.println("ID: " + artigo.getId() +
                                   ", Nome: " + artigo.getNome() +
                                   ", Tipo: " + artigo.getTipo().getNome() +
                                   ", Descrição: " + artigo.getDescricao());
            }
        }
        System.out.println("------------------------------------");
    }

    // 3. Adicionar Produtos
    public void adicionarProduto() {
        System.out.println("\n--- Adicionar Novo Artigo (Produto) ---");

        System.out.print("Nome do Artigo: ");
        String nome = scanner.nextLine();

        System.out.print("Descrição do Artigo: ");
        String descricao = scanner.nextLine();

        double preco = 0.0;
        boolean precoValido = false;
        while (!precoValido) {
            System.out.print("Preço do Artigo: ");
            try {
                preco = Double.parseDouble(scanner.nextLine());
                if (preco < 0) {
                    System.out.println("Preço não pode ser negativo. Tente novamente.");
                } else {
                    precoValido = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Preço inválido. Por favor, digite um número.");
            }
        }

        TipoArtigo tipoSelecionado = null;
        while (tipoSelecionado == null) {
            System.out.println("Tipos de Artigo disponíveis:");
            List<TipoArtigo> tipos = repositorio.getAllTiposArtigo();
            if (tipos.isEmpty()) {
                System.out.println("Nenhum tipo de artigo cadastrado. Crie um primeiro.");
                return;
            }
            for (TipoArtigo t : tipos) {
                System.out.println(t.getId() + " - " + t.getNome());
            }
            System.out.print("Selecione o ID do Tipo de Artigo (ou 'novo' para criar um): ");
            String tipoInput = scanner.nextLine();
            if (tipoInput.equalsIgnoreCase("novo")) {
                System.out.print("Nome do novo Tipo de Artigo: ");
                String novoNomeTipo = scanner.nextLine();
                tipoSelecionado = repositorio.addTipoArtigo(new TipoArtigo(repositorio.getNextTipoArtigoId(), novoNomeTipo));
                System.out.println("Novo Tipo de Artigo '" + novoNomeTipo + "' criado com ID " + tipoSelecionado.getId() + ".");
            } else {
                try {
                    int tipoId = Integer.parseInt(tipoInput);
                    tipoSelecionado = repositorio.getTipoArtigoById(tipoId);
                    if (tipoSelecionado == null) {
                        System.out.println("ID de Tipo de Artigo inválido. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Por favor, digite o ID ou 'novo'.");
                }
            }
        }

        Artigo novoArtigo = new Artigo(repositorio.getNextArtigoId(), nome, descricao, preco, tipoSelecionado);

        // Opcional: Adicionar respostas baseadas no checklist do tipo de artigo
        Checklist checklist = null;
        for (Checklist cl : repositorio.getAllChecklists()) {
            if (cl.getTipoArtigo().getId() == tipoSelecionado.getId()) {
                checklist = cl;
                break;
            }
        }

        if (checklist != null && !checklist.getPerguntas().isEmpty()) {
            System.out.println("\nEste tipo de artigo tem um checklist. Por favor, responda às perguntas:");
            for (Pergunta p : checklist.getPerguntas()) {
                System.out.print(p.getTexto() + ": ");
                String respostaTexto = scanner.nextLine();
                novoArtigo.addResposta(new Resposta(repositorio.getNextRespostaId(), p, respostaTexto));
            }
        } else {
            System.out.println("Nenhum checklist associado a este tipo de artigo ou checklist vazio.");
        }

        repositorio.addArtigo(novoArtigo);
        System.out.println("Artigo '" + novoArtigo.getNome() + "' adicionado com sucesso! ID: " + novoArtigo.getId());
        System.out.println("------------------------------------");
    }

    // 4. Pesquisar por Categoria (Tipo de Artigo)
    public void pesquisarPorCategoria() {
        System.out.println("\n--- Pesquisar Artigos por Categoria ---");

        TipoArtigo tipoSelecionado = null;
        while (tipoSelecionado == null) {
            System.out.println("Categorias (Tipos de Artigo) disponíveis:");
            List<TipoArtigo> tipos = repositorio.getAllTiposArtigo();
            if (tipos.isEmpty()) {
                System.out.println("Nenhuma categoria cadastrada.");
                return;
            }
            for (TipoArtigo t : tipos) {
                System.out.println(t.getId() + " - " + t.getNome());
            }
            System.out.print("Selecione o ID da Categoria: ");
            try {
                int tipoId = Integer.parseInt(scanner.nextLine());
                tipoSelecionado = repositorio.getTipoArtigoById(tipoId);
                if (tipoSelecionado == null) {
                    System.out.println("ID de Categoria inválido. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            }
        }

        List<Artigo> resultados = new ArrayList<>();
        for (Artigo artigo : repositorio.getAllArtigos()) {
        