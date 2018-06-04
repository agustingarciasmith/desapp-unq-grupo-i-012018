package ar.edu.unq.desapp.grupoi.model;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.contexts.TestContext;
import ar.com.dgarcia.javaspec.api.variable.Variable;
import ar.edu.unq.desapp.grupoi.model.errors.model.FieldMissing;
import ar.edu.unq.desapp.grupoi.model.support.PublicationBuilder;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JavaSpecRunner.class)
public class PublicationTests extends JavaSpec<TestContext> {

    @Override
    public void define() {
        describe("publication creation", () -> {
            Variable<PublicationBuilder> publicationBuilder = Variable.create();

            beforeEach(()-> {
                publicationBuilder.set(new PublicationBuilder());
            });


            it("publication gets created with appropriate arguments", () -> {
                Publication publication = publicationBuilder.get().build();

                assertThat(publication.getCity()).isEqualTo("Buenos Aires");
                assertThat(publication.getPickUpAddress()).isEqualTo("Quilmes 398");
                assertThat(publication.getReturnAddress()).containsExactly("Azcuenaga 2344");
                assertThat(publication.getContactPhone()).isEqualTo("123456789");
                assertThat(publication.getCost()).isEqualTo(400);
                assertThat(publication.getAvailableDates().contains(LocalDate.parse("2018-05-18")));
            });

            it("cant build publication without cost", () -> {
                try {
                    publicationBuilder.get().withCost(null).build();

                    Assertions.failBecauseExceptionWasNotThrown(FieldMissing.class);
                } catch (FieldMissing e) {
                    assertThat(e).hasMessage("Cost field is obligatory");
                }
            });
        });
    }
}
