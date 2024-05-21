package seng201.team0.services;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import seng201.team0.models.Cart;
import seng201.team0.models.Tower;

public class CartService{
    private boolean isIncrementIntoCart = false;
    private TranslateTransition cartTranslate = new TranslateTransition();
    private TranslateTransition progressTranslate = new TranslateTransition();
    private TranslateTransition labelTranslate = new TranslateTransition();
    private Cart cart;

    public CartService(Cart cart){
        this.cart = cart;
    }

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
//    public void incrementAmountResourceIntoCart(Tower tower) {
//        if(tower.getName().equals(cart.getTypeResourceCart())){
//            cart.setCurrentAmount(cart.getCurrentAmountOfCart() + tower.getResourceAmount());
//            this.isIncrementIntoCart = true;
//            if(cart.getCurrentAmountOfCart() > cart.getSizeOfCart())
//                cart.setCurrentAmount(cart.getSizeOfCart());
//        }
//    }

    /**
     * Check if the cart has been fulled or not
     * @return true if the cart has been filled up, false otherwise
     */
//    public boolean isCartFilledUp(){return currentAmount == sizeOfCart;}

    public TranslateTransition getCartTranslate() {return this.cartTranslate;}

    /**
     * Cart method to generate an animation sequence for the given elements on the screen.
     * @param cartImage element to move on the screen
     * @param progressbar element to move on the screen
     * @param resourceLabel element to move on the screen
     * @return The final TranslateTransition object in the sequence, such that win/lose conditions
     *         can be found on completion of the animation sequence
     */
//    public TranslateTransition generateAnimation (ImageView cartImage, ProgressBar progressbar, Label resourceLabel) {
//        // Translate Transition for cart image
//        double distanceX = 700;
//        double distanceY = 100;
//
//        double durationX = ((distanceX*1000) / cart.getSpeed());   // t = d / s
//        double durationY = ((distanceY*1000) / cart.getSpeed());

//        this.cartTranslate = this.getTranslation(cartImage, durationX, distanceX, 0);
//        TranslateTransition cartMove2 = this.getTranslation(cartImage, durationY, 0, distanceY);
//        TranslateTransition cartMove3 = this.getTranslation(cartImage, durationX, -distanceX, 0);
//        TranslateTransition cartMove4 = this.getTranslation(cartImage, durationY, 0, distanceY);
//        TranslateTransition cartMove5 = this.getTranslation(cartImage, durationX, distanceX, 0);
//
//        // Translate transition for cart progress bar
//        this.progressTranslate = this.getTranslation(progressbar,durationX,distanceX,0);
//        TranslateTransition progressMove2 = this.getTranslation(progressbar,durationY,0,distanceY);
//        TranslateTransition progressMove3 = this.getTranslation(progressbar,durationX,-distanceX,0);
//        TranslateTransition progressMove4 = this.getTranslation(progressbar,durationY,0,distanceY);
//        TranslateTransition progressMove5 = this.getTranslation(progressbar,durationX,distanceX,0);
//
//        // Translate transition for cart resource label
//        this.labelTranslate = this.getTranslation(resourceLabel,durationX,distanceX,0);
//        TranslateTransition labelMove2 = this.getTranslation(resourceLabel,durationY,0,distanceY);
//        TranslateTransition labelMove3 = this.getTranslation(resourceLabel,durationX,-distanceX,0);
//        TranslateTransition labelMove4 = this.getTranslation(resourceLabel,durationY,0,distanceY);
//        TranslateTransition labelMove5 = this.getTranslation(resourceLabel,durationX,distanceX,0);
//
//        resourceLabel.setText(cart.getTypeResourceCart());
//
//        this.cartTranslate.setOnFinished(actionEvent -> cartMove2.play());
//        cartMove2.setOnFinished(actionEvent -> cartMove3.play());
//        cartMove3.setOnFinished(actionEvent -> cartMove4.play());
//        cartMove4.setOnFinished(actionEvent -> cartMove5.play());
//
//        this.progressTranslate.setOnFinished(actionEvent -> progressMove2.play());
//        progressMove2.setOnFinished(actionEvent -> progressMove3.play());
//        progressMove3.setOnFinished(actionEvent -> progressMove4.play());
//        progressMove4.setOnFinished(actionEvent -> progressMove5.play());
//
//        this.labelTranslate.setOnFinished(actionEvent -> labelMove2.play());
//        labelMove2.setOnFinished(actionEvent -> labelMove3.play());
//        labelMove3.setOnFinished(actionEvent -> labelMove4.play());
//        labelMove4.setOnFinished(actionEvent -> labelMove5.play());

//        return cartMove5;
//    }

//    /**
//     * Cart method to start the animation
//     */
//    public void startAnimation() {
//        this.cartTranslate.play();
//        this.progressTranslate.play();
//        this.labelTranslate.play();
//    }

    /**
     * Private method to generate a single translate transition movement
     * @param image node to be translated
     * @param duration for the translation to occur over
     * @param x movement in x
     * @param y movement in y
     * @return Generated TranslateTransition object
     */
//    private TranslateTransition getTranslation(Node image, double duration, double x, double y) {
//        TranslateTransition translate = new TranslateTransition();
//        translate.setNode(image);
//        translate.setDuration(Duration.millis(duration));
//        translate.setByX(x);
//        translate.setByY(y);
//        return translate;
//    }
}
