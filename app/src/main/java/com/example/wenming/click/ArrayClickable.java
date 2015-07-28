package com.example.wenming.click;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.array.ArrayUtils;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.AnimatedSprite.IAnimationListener;
/**
 * Created by wenming on 2015-7-25.
 */
public class ArrayClickable {

    private final static int  MAXLEN = 4;
    int arrayXLen = 0;
    int arrayYLen = 0;

    AnimatedSprite [][] mSpriteArray = null;

    // init method to
    public ArrayClickable(Scene scene, int x, int y, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager vertexBufferObjectManager) {
        arrayXLen = (x < 0 || x > MAXLEN) ? MAXLEN : x;
        arrayYLen = (y < 0 || y > MAXLEN) ? MAXLEN : y;

        mSpriteArray = new AnimatedSprite[arrayXLen][arrayYLen];
        for (int indexX = 0; indexX < arrayXLen; indexX ++) {
            for(int indexY = 0; indexY < arrayYLen; indexY ++) {
                mSpriteArray[indexX][indexY] = new AnimatedSprite(50 * indexX, 50 * indexY,pTiledTextureRegion, vertexBufferObjectManager);
                scene.attachChild(mSpriteArray[indexX][indexY]);
            }
        }
    }

    public void startAnimate() {
        if (mSpriteArray == null)
            return;
        for (int indexX = 0; indexX < arrayXLen; indexX ++) {
            for(int indexY = 0; indexY < arrayYLen; indexY ++) {
                mSpriteArray[indexX][indexY].animate(200, mListener);

            }
        }
    }
    public void stopAnimate(int x, int y) {
        if (mSpriteArray == null)
            return;
        for (int indexX = 0; indexX < arrayXLen; indexX ++) {
            for(int indexY = 0; indexY < arrayYLen; indexY ++) {
                mSpriteArray[indexX][indexY].stopAnimation();
            }
        }
        if (isInLimit(x) && isInLimit(y))
            mSpriteArray[x][y].setCurrentTileIndex(1);
    }

    private boolean isInLimit(int data) {
        return (data >= 0 && data < MAXLEN) ? true : false;
    }

    public void setUserClicked(int x, int y) {

    }

    public void registerAnimationAction() {

    }


    public class AnimationListener implements  AnimatedSprite.IAnimationListener {
        public void onAnimationStarted(AnimatedSprite var1, int var2){

        }

        public void onAnimationFrameChanged(AnimatedSprite var1, int var2, int var3){

        }

        public void onAnimationLoopFinished(AnimatedSprite var1, int var2, int var3) {
            //var1.setCurrentTileIndex(0);
        }
        public void onAnimationFinished(AnimatedSprite var1) {
            if (var1 != null && var1 instanceof mSpriteArray[arrayXLen -1][arrayYLen -1])
                var1.setCurrentTileIndex(0);
        }
    }
}
