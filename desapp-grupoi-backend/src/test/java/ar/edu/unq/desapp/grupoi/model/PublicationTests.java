package ar.edu.unq.desapp.grupoi.model;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.TestContext;
import ar.com.dgarcia.javaspec.api.Variable;
import ar.edu.unq.desapp.grupoi.model.errors.FieldMissing;
import ar.edu.unq.desapp.grupoi.model.support.PublicationBuilder;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JavaSpecRunner.class)
public class PublicationTests extends JavaSpec<TestContext>{

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
                assertThat(publication.getPickUpAdress()).isEqualTo("pick up address");
                assertThat(publication.getReturnAdress()).isEqualTo("return address");
                assertThat(publication.getContactPhone()).isEqualTo("123456789");
                assertThat(publication.getCost()).isEqualTo(400);
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
