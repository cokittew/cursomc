package com.gustavoferreira.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gustavoferreira.cursomc.domain.Categoria;
import com.gustavoferreira.cursomc.domain.Cidade;
import com.gustavoferreira.cursomc.domain.Cliente;
import com.gustavoferreira.cursomc.domain.Endereco;
import com.gustavoferreira.cursomc.domain.Estado;
import com.gustavoferreira.cursomc.domain.ItemPedido;
import com.gustavoferreira.cursomc.domain.Pagamento;
import com.gustavoferreira.cursomc.domain.PagamentoComBoleto;
import com.gustavoferreira.cursomc.domain.PagamentoComCartao;
import com.gustavoferreira.cursomc.domain.Pedido;
import com.gustavoferreira.cursomc.domain.Produto;
import com.gustavoferreira.cursomc.domain.enums.EstadoPagamento;
import com.gustavoferreira.cursomc.domain.enums.TipoCliente;
import com.gustavoferreira.cursomc.repositories.CategoriaRepository;
import com.gustavoferreira.cursomc.repositories.CidadeRepository;
import com.gustavoferreira.cursomc.repositories.ClienteRepository;
import com.gustavoferreira.cursomc.repositories.EnderecoRepository;
import com.gustavoferreira.cursomc.repositories.EstadoRepository;
import com.gustavoferreira.cursomc.repositories.ItemPedidoRepository;
import com.gustavoferreira.cursomc.repositories.PagamentoRepository;
import com.gustavoferreira.cursomc.repositories.PedidoRepository;
import com.gustavoferreira.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository catRepo;
	@Autowired
	private ProdutoRepository prodRepo;
	@Autowired
	private EstadoRepository estaRepo;
	@Autowired
	private CidadeRepository cidaRepo;
	@Autowired
	private ClienteRepository cliRepo;
	@Autowired
	private EnderecoRepository endeRepo;
	@Autowired
	private PedidoRepository pedRepo;
	@Autowired
	private PagamentoRepository pagRepo;
	@Autowired
	private ItemPedidoRepository ipRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("157896554","48945545"));
		
		Endereco e1 = new Endereco(null, "Rua Flores","300", "Apto 203", "Jardim", "32880834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos","105", "Sala 800", "Centro", "38770834",cli1, c1);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));

		catRepo.saveAll(Arrays.asList(cat1, cat2));
		prodRepo.saveAll(Arrays.asList(p1,p2,p3));
		estaRepo.saveAll(Arrays.asList(est1, est2));
		cidaRepo.saveAll(Arrays.asList(c1,c2,c3));
		cliRepo.saveAll(Arrays.asList(cli1));
		endeRepo.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 23:59"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedRepo.saveAll(Arrays.asList(ped1, ped2));
		pagRepo.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1 , 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.0);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.0, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		ipRepo.saveAll(Arrays.asList(ip1, ip2, ip3));
		
		
	}

}
