package pl.kocjan.automatizer.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

import io.vavr.control.Either;
import pl.kocjan.automatizer.domain.common.vavr.Error;
import pl.kocjan.automatizer.domain.parser.YamlParser;
import pl.kocjan.automatizer.domain.playbook.dto.PlaybookDto;

public class YamlParserTest {
	
	private final String VALID_HOST_GROUP_FROM_FILE = "database";
	
	@Test
	public void shouldCorrectlyParseYamlFile() {
		//given
		File validYamlFile = new File("src/test/java/pl/kocjan/automatizer/parser/test_valid.yaml");
		YamlParser parser = new YamlParser();
		
		//when
		Either<Error, PlaybookDto> result = parser.yamlToPlaybook(validYamlFile);
		//then		
		assertTrue(result.isRight());
		assertEquals(VALID_HOST_GROUP_FROM_FILE, result.get().getHostGroup());	
	}
	
	@Test
	public void shouldFailWithInvalidYamlFile() {
		//given
		File invalidYamlFile = new File("src/test/java/pl/kocjan/automatizer/parser/test_invalid.yaml");
		YamlParser parser = new YamlParser();
		
		//when
		Either<Error, PlaybookDto> result = parser.yamlToPlaybook(invalidYamlFile);
		
		//then		
		assertTrue(result.isLeft());
	}
}
