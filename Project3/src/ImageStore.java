import processing.core.PImage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ImageStore {
    private Map<String, PImage> images;

    public ImageStore() {
        this.images = new HashMap<>();
    }

    public void addImage(String key, PImage image){
        images.put(key, image);
    }

    public PImage getImage(String key){
        return images.get(key);
    }
}
