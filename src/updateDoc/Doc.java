package updateDoc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;

public class Doc {

	public static void main(String[] args) throws IOException, InvalidFormatException {
		criarArquivoDocx();
		alterarTemplateDocx();
	}

	public static void criarArquivoDocx() throws IOException {
		XWPFDocument doc = new XWPFDocument();
		XWPFParagraph paragrafo = doc.createParagraph();
		XWPFRun tmpRun = paragrafo.createRun();
		tmpRun.setText("bla bla bla");

		escreverArquivo(doc, "c:/temp/", "textWord.docx");
	}

	public static void alterarTemplateDocx() throws IOException, InvalidFormatException {
		XWPFDocument doc = new XWPFDocument(new FileInputStream("template/template.docx"));
		for (XWPFParagraph paragrafo : doc.getParagraphs()) {
			List<XWPFRun> runs = paragrafo.getRuns();
			if (runs != null) {
				for (XWPFRun r : runs) {
					String text = r.getText(0);
					if (text != null && text.contains("[numero]")) {
						text = text.replace("[numero]", "123456");
						r.setText(text, 0);
					}
					if (text != null && text.contains("[estabelecimento]")) {
						text = text.replace("[estabelecimento]", "dogão");
						r.setText(text, 0);
					}
				}
			}
		}

		List<String> tabela = new ArrayList<String>();
		tabela.add("nome");
		tabela.add("telefone");
		tabela.add("endereço");
		XWPFTable table = doc.getTables().get(0);
		for (int i = 0; i < tabela.size(); i++) {
			table.createRow().getCell(0).setText(tabela.get(i));
		}

		escreverArquivo(doc, "c:/temp/", "arquivoNovo.docx");
		doc.close();
	}

	private static void escreverArquivo(XWPFDocument doc, String pasta, String nomeArquivo) throws IOException {

		File f = new File(pasta);
		if (!f.exists()) {
			System.out.println("Criando pasta " + pasta);
			f.mkdirs();
		}

		FileOutputStream out = new FileOutputStream(new File(pasta + nomeArquivo));
		doc.write(out);
		doc.close();

		System.out.println("Escrevendo arquivo " + pasta + nomeArquivo);
		out.close();
	}
}
