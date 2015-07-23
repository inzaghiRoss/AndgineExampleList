package com.example.wenming.andgineexamplelist;

import android.os.Handler;

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
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.debug.Debug;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga
 *
 * @author Nicolas Gramlich
 * @since 11:54:51 - 03.04.2010
 */
public class AnimatedSpritesExample extends SimpleBaseGameActivity {
	// ===========================================================
	// Constants
	// ===========================================================
	private static final String TAG = "examplelist";
	private static final String SUB_TAG = "AnimatedSpritesExample";


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
	private Handler mHandler = null;
	AnimatedSprite face1 = null;

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
		} catch (TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	}

	@Override
	public Scene onCreateScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());

		final Scene scene = new Scene();
		scene.setBackground(new Background(0.09804f, 0.6274f, 0.8784f));

		/* Quickly twinkling face. */
		face1 = new AnimatedSprite(100, 50, this.mFaceTextureRegion, this.getVertexBufferObjectManager());
		//face1.setCurrentTileIndex(1);
		face1.animate(100, 4, new AnimationListener());

		scene.attachChild(face1);





		final TiledSprite face2 = new TiledSprite(200, 50, this.mFaceTextureRegion, this.getVertexBufferObjectManager());
		face2.setCurrentTileIndex(0);
		scene.attachChild(face2);

		/* Continuously flying helicopter. */
		final AnimatedSprite helicopter = new AnimatedSprite(320, 50, this.mHelicopterTextureRegion, this.getVertexBufferObjectManager());
		helicopter.animate(new long[] { 100, 100 }, 1, 2, true);
		scene.attachChild(helicopter);

		/* Snapdragon. */
		final AnimatedSprite snapdragon = new AnimatedSprite(300, 200, this.mSnapdragonTextureRegion, this.getVertexBufferObjectManager());
		snapdragon.animate(100);
		scene.attachChild(snapdragon);

		/* Funny banana. */
		final AnimatedSprite banana = new AnimatedSprite(100, 220, this.mBananaTextureRegion, this.getVertexBufferObjectManager());
		banana.animate(100);
		scene.attachChild(banana);

		return scene;
	}

	public class AnimationListener implements  AnimatedSprite.IAnimationListener {
		public void onAnimationStarted(AnimatedSprite var1, int var2){

		}

		public void onAnimationFrameChanged(AnimatedSprite var1, int var2, int var3){

		}

		public void onAnimationLoopFinished(AnimatedSprite var1, int var2, int var3) {
			var1.setCurrentTileIndex(0);
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
