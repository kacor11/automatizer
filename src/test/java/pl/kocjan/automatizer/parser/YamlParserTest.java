package pl.kocjan.automatizer.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import io.vavr.control.Either;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.parser.YamlParser;
import pl.kocjan.automatizer.domain.playbook.dto.PlaybookDto;

public class YamlParserTest {
	
	private final String VALID_HOST_GROUP_FROM_FILE = "DEFAULT";
	
	@Test
	public void shouldCorrectlyParseYamlFile() {
		//given
		byte[] fileContent;
		try {
			fileContent = Files.readAllBytes(Paths.get("src/test/java/pl/kocjan/automatizer/parser/test_valid.yaml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		YamlParser parser = new YamlParser();
		
		//when
		Either<Error, PlaybookDto> result = parser.yamlToPlaybook(fileContent);
		//then		
		System.out.println(result.isRight() + "is right?");
		System.out.println(fileContent.length + "length");
		assertTrue(result.isRight());
		assertEquals(VALID_HOST_GROUP_FROM_FILE, result.get().getHostGroup());	
		assertEquals(2, result.get().getTasks().size());
	}
	
	@Test
	public void shouldFailWithInvalidYamlFile() {
		//given
		byte[] fileContent;
		try {
			fileContent = Files.readAllBytes(Paths.get("src/test/java/pl/kocjan/automatizer/parser/test_invalid.yaml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		YamlParser parser = new YamlParser();
		
		//when
		Either<Error, PlaybookDto> result = parser.yamlToPlaybook(fileContent);
		
		//then		
		assertTrue(result.isLeft());
	}
}
