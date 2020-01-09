package test;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import updateDoc.Doc;

public class CriandoArquivoTest {

	@Test
	public void deve_criar_um_arquivo_docx() throws IOException {
		Doc.criarArquivoDocx();
		File arquivo = new File("c:/temp/textWord.docx");
		Assert.assertTrue(arquivo.exists());
	}

	@Test
	public void deve_criar_um_arquivo_docx_a_partir_do_arquivo_template() throws InvalidFormatException, IOException {
		Doc.alterarTemplateDocx();
		File arquivo = new File("c:/temp/arquivoNovo.docx");
		Assert.assertTrue(arquivo.exists());
	}
}
