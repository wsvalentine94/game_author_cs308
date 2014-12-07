package authoring.view.graphicsview;

import java.io.File;

import authoring.eventhandlers.GameHandler;
import authoring.view.propertiesview.GameObjectProperties;
import authoring.view.propertiesview.Properties;
import engine.gameObject.GameObject;

public class GameObjectGraphic extends Graphic{

	private GameObject myGameObject;
	
	public GameObjectGraphic(GameObject gameObject, File location, GameHandler ...event) {
		super(gameObject.getCurrentImageName(), location, event);
		myGameObject = gameObject;
	}
	
	public GameObject getGameObject(){
		return myGameObject;
	}

	@Override
	public Properties makeProperties() {
		return new GameObjectProperties(this);
		// TODO Auto-generated method stub
		
	}

}
