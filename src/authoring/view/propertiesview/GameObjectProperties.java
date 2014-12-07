package authoring.view.propertiesview;

import static authoring.view.levelview.SingleLevelView.OBJECT_X_OFFSET;
import static authoring.view.levelview.SingleLevelView.OBJECT_Y_OFFSET;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import authoring.eventhandlers.GameHandler;
import engine.gameObject.GameObject;
import engine.gameObject.components.PhysicsBody;
import engine.physics.CollisionConstant;
import engine.physics.Vector;

public class GameObjectProperties extends Properties {

	private Map<String, PropertyTextField> inherentTextProperties;
	private Map<String, PropertyTextField> concreteTextProperties;
	private Map<String, CheckBox> booleanProperties;
	private GameHandler myEditHandler;
	private GameHandler myDeleteHandler;
	private GameHandler mySaveAsNewHandler;

	public GameObjectProperties(GameObject gObj, GameHandler ...handler) {
		myEditHandler = handler[0];
		mySaveAsNewHandler = handler[1];
		myDeleteHandler = handler[2];
		initializeProperties(gObj);
	}

	public GameObjectProperties(){
		//nullary constructor that creates empty map to generate new game objects
		//consider refactoring this
		inherentTextProperties = new HashMap<String, PropertyTextField>();
		
		inherentTextProperties.put("name", new PropertyTextField("Name: ", ""));
		inherentTextProperties.put("image", new PropertyTextField("Image: ", ""));		
		inherentTextProperties.put("collision", new PropertyTextField("Collision Constant", ""));
		inherentTextProperties.put("initXV", new PropertyTextField("Initial X Velocity", ""));
		inherentTextProperties.put("initYV", new PropertyTextField("Initial Y Velocity", ""));
		inherentTextProperties.put("width", new PropertyTextField("Width: ", ""));
		inherentTextProperties.put("height",new PropertyTextField("Height: ", ""));
	}
	
	@Override
	public void initializeProperties(Object g) {

		GameObject gameObject = (GameObject) g;

		this.getChildren().clear();

		inherentTextProperties = new HashMap<String, PropertyTextField>();
		concreteTextProperties = new HashMap<String, PropertyTextField>();
		booleanProperties = new HashMap<String, CheckBox>();

		inherentTextProperties.put("name",
				new PropertyTextField("Name: ", gameObject.getID()));
		inherentTextProperties.put(
				"image",
				new PropertyTextField("Image: ", gameObject
						.getCurrentImageName()));
		
		inherentTextProperties.put("collision", new PropertyTextField("Collision Constant", "0"));
		inherentTextProperties.put("initXV", new PropertyTextField("Initial X Velocity", "0"));
		inherentTextProperties.put("initYV", new PropertyTextField("Initial Y Velocity", "0"));
		inherentTextProperties.put("width", new PropertyTextField("Width: ", Double.toString(gameObject.getWidth())));
		inherentTextProperties.put("height",new PropertyTextField("Height: ", Double.toString(gameObject.getHeight())));
		
		
		concreteTextProperties.put("x",new PropertyTextField("X: ", Double.toString(gameObject.getX()+ OBJECT_X_OFFSET)));
		concreteTextProperties.put("y",new PropertyTextField("Y: ", Double.toString(gameObject.getY()+ OBJECT_Y_OFFSET)));
		concreteTextProperties.put("rotation", new PropertyTextField("Rotation: ", Double.toString(gameObject.getRotation())));
	
		

		for (String s : inherentTextProperties.keySet()) {
			this.getChildren().add(inherentTextProperties.get(s));
		}
		for(String s: concreteTextProperties.keySet()){
			this.getChildren().add(concreteTextProperties.get(s));
		}

		HBox visibilityField = new HBox();
		CheckBox cbVisibility = new CheckBox("Enabled");
		cbVisibility.setSelected(gameObject.isEnabled());
		visibilityField.getChildren().add(cbVisibility);
		booleanProperties.put("enabled", cbVisibility);

		HBox physicsBody = new HBox();
		CheckBox cbPhysics = new CheckBox("Physics Body");
		cbPhysics.setSelected(gameObject.getPhysicsBody() != null);
		physicsBody.getChildren().add(cbPhysics);
		booleanProperties.put("has physics", cbPhysics);
		
		for(String s: booleanProperties.keySet()){
			this.getChildren().add(booleanProperties.get(s));
		}

		Button editButton = new Button("Edit");
		editButton.setOnAction(myEditHandler);
		this.getChildren().add(editButton);

		Button saveAsNew = new Button("Save as New");
		saveAsNew.setOnMouseClicked(mySaveAsNewHandler);
		this.getChildren().add(saveAsNew);
		
		Button delete = new Button("Delete");
		delete.setOnAction(myDeleteHandler);
		this.getChildren().add(delete);

	}

	public GameObject edit(GameObject g) {

		GameObject edited = new GameObject(g.getComponents(), inherentTextProperties
				.get("image").getInformation(),
				Double.parseDouble(concreteTextProperties.get("x").getInformation())
						- OBJECT_X_OFFSET, Double.parseDouble(concreteTextProperties
						.get("y").getInformation()) - OBJECT_Y_OFFSET,
				Double.parseDouble(inherentTextProperties.get("height")
						.getInformation()), Double.parseDouble(inherentTextProperties
						.get("width").getInformation()),
				Double.parseDouble(concreteTextProperties.get("rotation")
						.getInformation()), inherentTextProperties.get("name")
						.getInformation());
		
		
		if (booleanProperties.get("has physics").isSelected()) {
			
			PhysicsBody pb = new PhysicsBody(Double.parseDouble(inherentTextProperties
					.get("height").getInformation()),
					Double.parseDouble(inherentTextProperties.get("width")
							.getInformation()));

			pb.addScalar(new CollisionConstant(Double.parseDouble(inherentTextProperties.get("collision").getInformation())));
			pb.setVelocity(new Vector(Double.parseDouble(inherentTextProperties.get("initXV").getInformation()), Double.parseDouble(inherentTextProperties.get("initYV").getInformation())));
			
			System.out.println(pb);
			edited.setPhysicsBody(pb);
		}
	
		return edited;
	}

	public Button setUpForNewObject() {
		this.getChildren().clear();
		for (String s : inherentTextProperties.keySet()) {
			PropertyTextField cleared = inherentTextProperties.get(s);
			cleared.setString("");
			this.getChildren().add(cleared);
		}
 
		for(String s: booleanProperties.keySet()){
			this.getChildren().add(booleanProperties.get(s));
		}
		
		Button b = new Button("Create");
		this.getChildren().add(b);
		return b;
	}

	public void saveAsNew() {

	}
	
	public Map<String, PropertyTextField> getMap(){
		return inherentTextProperties;
	}
}
