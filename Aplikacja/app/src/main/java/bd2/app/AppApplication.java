package bd2.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AppApplication extends Application {

	private ConfigurableApplicationContext springContext;
	private Parent rootNode;
	private FXMLLoader fxmlLoader;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() throws Exception {
		springContext = SpringApplication.run(AppApplication.class);
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(springContext::getBean);
	}

	@Override
	public void start(Stage primaryStage) throws Exception{
		fxmlLoader.setLocation(getClass().getResource("/fxml/widok1.fxml"));
		rootNode = fxmlLoader.load();

		primaryStage.setTitle("Sport tournaments app");
		Scene scene = new Scene(rootNode, 1200, 680);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void stop() {
		springContext.stop();
	}

}

