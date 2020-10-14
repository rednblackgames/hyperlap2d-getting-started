package games.rednblack.hyperlap2d.example;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import games.rednblack.editor.renderer.SceneLoader;
import games.rednblack.editor.renderer.systems.action.Actions;
import games.rednblack.editor.renderer.systems.action.data.ActionData;
import games.rednblack.editor.renderer.utils.ItemWrapper;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class HyperLap2DExample extends ApplicationAdapter {

    private SceneLoader mSceneLoader;
    private Viewport mViewport;
    private Camera mCamera;

    @Override
    public void create() {
        //Create a basic camera and a viewport
        mCamera = new OrthographicCamera();
        mViewport = new ExtendViewport(360, 640, 0, 0, mCamera);

        //Initialize HyperLap2D's SceneLoader, all assets will be loaded here
        mSceneLoader = new SceneLoader();
        //Load the desired scene with custom viewport
        mSceneLoader.loadScene("MainScene", mViewport);

        //Get root Entity and create the main `ItemWrapper` object
        ItemWrapper root = new ItemWrapper(mSceneLoader.getRoot());

        //Retrieve from root a particular entitiy from its unique name
        Entity skull3 = root.getChild("level-3-skull").getEntity();

        //Load ActionData from Actions Library of the project
        ActionData actionData = mSceneLoader.loadActionFromLibrary("test");

        //Attach action to the entity
        Actions.addAction(mSceneLoader.getEngine(),  skull3, actionData);
    }

    @Override
    public void render() {
        mCamera.update(); //Update camera

        //Clear screen
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.15f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Apply ViewPort and update SceneLoader's ECS engine
        mViewport.apply();
        mSceneLoader.getEngine().update(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void resize(int width, int height) {
        mViewport.update(width, height);
    }
}