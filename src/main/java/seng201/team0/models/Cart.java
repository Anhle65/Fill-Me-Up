package seng201.team0.models;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Cart {
    private double cartSpeed;
    private final String typeResourceCart;
    private final int sizeOfCart;
    private int currentAmount = 0;
    private boolean isIncrementIntoCart = false;

    private TranslateTransition cartTranslate = new TranslateTransition();
    private TranslateTransition progressTranslate = new TranslateTransition();
    private TranslateTransition labelTranslate = new TranslateTransition();

    public Cart(String inputResoure, double inputSpeed, int inputSize ) {
        this.typeResourceCart = inputResoure;
        this.cartSpeed = inputSpeed;
        this.sizeOfCart = inputSize;
    }

    /**
     * Get the type of cart
     * @return typeResourceCart
     */
    public String getTypeResourceCart(){return this.typeResourceCart;}

    /**
     * Get the size of cart
     * @return sizeOfCart
     */
    public int getSizeOfCart(){return this.sizeOfCart;}

    /**
     * Get the speed of cart
     * @return speed
     */
    public double getSpeed(){return this.cartSpeed;}

    /**
     * Set the speed of cart
     */
    public void setCartSpeed(double speed) { this.cartSpeed = speed;}

    /**
     * Get the condition to increment into cart if it is true, no action otherwise
     * @return boolean isIncrementIntoCart
     */
    public boolean getIsIncrementIntoCart(){
        return this.isIncrementIntoCart;
    }

    /**
     * When called, set the isIncrementIntoCart to false, which is used after increment in cart
     */
    public void setIncrementIntoCartToFalse(){this.isIncrementIntoCart = false;}

    /**
     * Increment the amount resource into cart if the input is the same type with cart's type
     */
    public void incrementAmountResourceIntoCart(Tower tower) {
        if(tower.getName().equals(this.typeResourceCart)){
            currentAmount += tower.getResourceAmount();
            this.isIncrementIntoCart = true;
            if(currentAmount > sizeOfCart)
                currentAmount = sizeOfCart;
        }
    }

    /**
     * Get the current amount that cart has been filled
     * @return currentAmount
     */
    public int getCurrentAmountOfCart(){return currentAmount;}

    /**
     * Check if the cart has been fulled or not
     * @return true if the cart has been filled up, false otherwise
     */
    public boolean isCartFilledUp(){return currentAmount == sizeOfCart;}

    public TranslateTransition getCartTranslate() {return this.cartTranslate;}

    /**
     * Cart method to generate an animation sequence for the given elements on the screen.
     * @param cartImage element to move on the screen
     * @param progressbar element to move on the screen
     * @param resourceLabel element to move on the screen
     * @return The final TranslateTransition object in the sequence, such that win/lose conditions
     *         can be found on completion of the animation sequence
     */
    public TranslateTransition generateAnimation (ImageView cartImage, ProgressBar progressbar, Label resourceLabel) {
        // Translate Transition for cart image
        double DurX = 4000;
        double DurY = 3000;

        this.cartTranslate = this.getTranslation(cartImage, DurX, 700, 0);
        TranslateTransition cartMove2 = this.getTranslation(cartImage, DurY, 0, 100);
        TranslateTransition cartMove3 = this.getTranslation(cartImage, DurX, -700, 0);
        TranslateTransition cartMove4 = this.getTranslation(cartImage, DurY, 0, 100);
        TranslateTransition cartMove5 = this.getTranslation(cartImage, DurX, 700, 0);

        // Translate transition for cart progress bar
        this.progressTranslate = this.getTranslation(progressbar,DurX,700,0);
        TranslateTransition progressMove2 = this.getTranslation(progressbar,DurY,0,100);
        TranslateTransition progressMove3 = this.getTranslation(progressbar,DurX,-700,0);
        TranslateTransition progressMove4 = this.getTranslation(progressbar,DurY,0,100);
        TranslateTransition progressMove5 = this.getTranslation(progressbar,DurX,700,0);

        // Translate transition for cart resource label
        this.labelTranslate = this.getTranslation(resourceLabel,DurX,700,0);
        TranslateTransition labelMove2 = this.getTranslation(resourceLabel,DurY,0,100);
        TranslateTransition labelMove3 = this.getTranslation(resourceLabel,DurX,-700,0);
        TranslateTransition labelMove4 = this.getTranslation(resourceLabel,DurY,0,100);
        TranslateTransition labelMove5 = this.getTranslation(resourceLabel,DurX,700,0);

        resourceLabel.setText(this.typeResourceCart);

        this.cartTranslate.setOnFinished(actionEvent -> cartMove2.play());
        cartMove2.setOnFinished(actionEvent -> cartMove3.play());
        cartMove3.setOnFinished(actionEvent -> cartMove4.play());
        cartMove4.setOnFinished(actionEvent -> cartMove5.play());

        this.progressTranslate.setOnFinished(actionEvent -> progressMove2.play());
        progressMove2.setOnFinished(actionEvent -> progressMove3.play());
        progressMove3.setOnFinished(actionEvent -> progressMove4.play());
        progressMove4.setOnFinished(actionEvent -> progressMove5.play());

        this.labelTranslate.setOnFinished(actionEvent -> labelMove2.play());
        labelMove2.setOnFinished(actionEvent -> labelMove3.play());
        labelMove3.setOnFinished(actionEvent -> labelMove4.play());
        labelMove4.setOnFinished(actionEvent -> labelMove5.play());

        return cartMove5;
    }

    /**
     * Cart method to start the animation
     */
    public void startAnimation() {
        this.cartTranslate.play();
        this.progressTranslate.play();
        this.labelTranslate.play();
    }

    /**
     * Private method to generate a single translate transition movement
     * @param image node to be translated
     * @param duration for the translation to occur over
     * @param x movement in x
     * @param y movement in y
     * @return Generated TranslateTransition object
     */
    private TranslateTransition getTranslation(Node image, double duration, double x, double y) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(image);
        translate.setDuration(Duration.millis(duration));
        translate.setByX(x);
        translate.setByY(y);
        return translate;
    }
}
