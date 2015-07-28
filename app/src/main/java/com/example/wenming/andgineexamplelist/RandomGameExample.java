package com.example.wenming.andgineexamplelist;

import android.os.Handler;
import android.os.Message;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.util.debug.Debug;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import com.example.wenming.click.ArrayClickable;

/**
 * Created by sunny on 2015-7-28.
 */
public class RandomGameExample extends SimpleBaseGameActivity {
    // ===========================================================
    // Constants
    // ===========================================================
    private static final String TAG = "examplelist";
    private static final String SUB_TAG = "RandomGameExample";


    private static final int CAMERA_WIDTH = 480;
    private static final int CAMERA_HEIGHT = 320;

    // ===========================================================
    // Fields
    // ===========================================================

    private BuildableBitmapTextureAtlas mBitmapTextureAtlas;

    private TiledTextureRegion mSnapdragonTextureRegion;
    private TiledTextureRegion mHelicopterTextureRegion;
    private TiledTextureRegion mBananaTextureRegion;
    private TiledTextureRegion mFaceTextureRegion;
   // private Handler mHandler = null;
    private static final int MSG_STOP_ANIMATION = 100;
    AnimatedSprite face1 = null;
    ArrayClickable randomView = null;


    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================



    @Override
    public EngineOptions onCreateEngineOptions() {
        final Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

        return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
    }

    @Override
    public void onCreateResources() {

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

        this.mBitmapTextureAtlas = new BuildableBitmapTextureAtlas(this.getTextureManager(), 512, 256, TextureOptions.NEAREST);
//		this.mBitmapTextureAtlas = new BuildableBitmapTextureAtlas(this.getTextureManager(), 512, 256, TextureOptions.BILINEAR);

        this.mSnapdragonTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "snapdragon_tiled.png", 4, 3);
        this.mHelicopterTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "helicopter_tiled.png", 2, 2);
        this.mBananaTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "banana_tiled.png", 4, 2);
        this.mFaceTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "face_box_tiled.png", 2, 1);

        try {
            this.mBitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 1));
            this.mBitmapTextureAtlas.load();
        } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            Debug.e(e);
        }
    }

    @Override
    public Scene onCreateScene() {
        this.mEngine.registerUpdateHandler(new FPSLogger());

        final Scene scene = new Scene();
        scene.setBackground(new Background(0.09804f, 0.6274f, 0.8784f));

        randomView = new ArrayClickable(scene, 3, 3, mFaceTextureRegion, this.getVertexBufferObjectManager());
        randomView.startAnimate();
        mHandler.sendEmptyMessageDelayed(MSG_STOP_ANIMATION, 15 * 1000);
        return scene;
    }

    @Override
    public void onResume() {
        super.onResume();

    }


    private Handler mHandler = new Handler() {
        public void handleMessage(Message e) {
            switch(e.what) {
                case MSG_STOP_ANIMATION:
                    if (randomView != null)
                        randomView.stopAnimate(2, 2);
                    break;
                default:
                    super.handleMessage(e);
            }
        }
    };

    public class AnimationListener implements  AnimatedSprite.IAnimationListener {
        public void onAnimationStarted(AnimatedSprite var1, int var2){

        }

        public void onAnimationFrameChanged(AnimatedSprite var1, int var2, int var3){

        }

        public void onAnimationLoopFinished(AnimatedSprite var1, int var2, int var3) {
            //var1.setCurrentTileIndex(0);
        }
        public void onAnimationFinished(AnimatedSprite var1) {
            var1.setCurrentTileIndex(0);
        }
    }


    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
