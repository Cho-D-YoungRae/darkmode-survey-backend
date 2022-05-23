package sejong.hci.darkmodesurveybackend.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import sejong.hci.darkmodesurveybackend.dto.findingword.FindingWordQuestionsDto;
import sejong.hci.darkmodesurveybackend.entity.FindingWord;

@Configuration
@EnableJpaAuditing
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(FindingWord.class, FindingWordQuestionsDto.class);
        return modelMapper;
    }
}
