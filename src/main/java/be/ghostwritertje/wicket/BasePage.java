package be.ghostwritertje.wicket;

import be.ghostwritertje.domain.Person;
import be.ghostwritertje.wicket.money.StatementListPage;
import be.ghostwritertje.wicket.person.pages.LoginPage;
import be.ghostwritertje.wicket.person.pages.PersonListPage;
import be.ghostwritertje.wicket.person.pages.RegisterPage;
import org.apache.wicket.markup.html.GenericWebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;

/**
 * Created by Ghostwritertje
 * Date: 30-Sep-16.
 */
public abstract class BasePage<T> extends GenericWebPage<T> {
    protected BasePage() {
        super();
    }

    protected BasePage(IModel<T> model) {
        super(model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        this.add(new BookmarkablePageLink("usersLink", PersonListPage.class));
        this.add(new BookmarkablePageLink("registerLink", RegisterPage.class)
                .add(new VisibilityBehavior<>(component -> CustomSession.get().getLoggedInPerson() == null)));
        this.add(new BookmarkablePageLink("loginLink", LoginPage.class)
                .add(new VisibilityBehavior<>(component -> CustomSession.get().getLoggedInPerson() == null)));

        this.add(new Label("loggedInUsername", new LambdaModel<>(() -> CustomSession.get().getLoggedInPerson().getUsername(), s -> {
        })).add(new VisibilityBehavior<>(component -> CustomSession.get().getLoggedInPerson() != null)));


        this.add(new Link<T>("statementsLink", this.getModel()) {
            @Override
            public void onClick() {
                this.setResponsePage(new StatementListPage(new Model<Person>(CustomSession.get().getLoggedInPerson())));
            }

            @Override
            protected void onInitialize() {
                super.onInitialize();
                this.add(new VisibilityBehavior<>(component -> CustomSession.get().getLoggedInPerson() != null));
            }
        });

    }


}
