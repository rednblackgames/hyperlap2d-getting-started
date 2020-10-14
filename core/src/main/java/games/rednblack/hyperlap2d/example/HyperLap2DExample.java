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
        mCamera = new OrthographicCamera();
        mViewport = new ExtendViewport(360, 640, 0, 0, mCamera);
        mSceneLoader = new SceneLoader();
        mSceneLoader.loadScene("MainScene", mViewport);

        //Add action from library
        ItemWrapper root = new ItemWrapper(mSceneLoader.getRoot());

        Entity skull3 = root.getChild("level-3-skull").getEntity();
        ActionData actionData = mSceneLoader.loadActionFromLibrary("test");
        Actions.addAction(mSceneLoader.getEngine(),  skull3, actionData);
    }

    @Override
    public void render() {
        mCamera.update();

        Gdx.gl.glClearColor(0.15f, 0.15f, 0.15f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mViewport.apply();
        mSceneLoader.getEngine().update(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void resize(int width, int height) {
        mViewport.update(width, height);
    }
}