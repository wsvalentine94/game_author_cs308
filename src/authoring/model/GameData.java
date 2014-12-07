package authoring.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import authoring.model.collections.ConditionsCollection;
import authoring.model.collections.GameObjectsCollection;
import authoring.model.collections.GeneralCollection;
import authoring.model.collections.ImagesCollection;
import authoring.model.collections.LevelsCollection;
import authoring.model.collections.SoundsCollection;
import engine.conditions.Condition;
import engine.level.*;
import engine.gameObject.GameObject;

/**
 * Passive data object that holds onto all of the data that represents an
 * authored game.
 * 
 * @author Chris Bernt
 * @author Wesley Valentine
 * @author Arjun Jain
 * @author Kevin Li
 *
 */
public class GameData {

	/**
	 * Maybe put in properties file?
	 */
	private LevelsCollection myLevels;
	private ConditionsCollection myConditions;
	private GameObjectsCollection myGameObjects;
	private ImagesCollection myImages;
	private SoundsCollection mySounds;

	public GameData() {
		myLevels = new LevelsCollection();
		myConditions = new ConditionsCollection();
		myGameObjects = new GameObjectsCollection();
		myLevels = new LevelsCollection();
		myImages = new ImagesCollection();
		mySounds = new SoundsCollection();
	}

	public LevelsCollection getLevels() {
		return myLevels;
	}

	/**
	 * GameObject Methods
	 */

	public GameObjectsCollection getGameObjects() {
		return myGameObjects;
	}


	/**
	 * Condition Methods
	 */

	public ConditionsCollection getConditions() {
		return myConditions;
	}

	/**
	 * Graphic Methods
	 */

	public ImagesCollection getImages() {
		return myImages;
	}

	/**
	 * Sound Methods
	 */

	public SoundsCollection getSounds() {
		return mySounds;
	}

}
