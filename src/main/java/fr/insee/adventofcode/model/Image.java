package fr.insee.adventofcode.model;

import java.util.List;

public class Image {
    private List<Layer> layers;

    /**
     * @return the layers
     */
    public List<Layer> getLayers() {
        return layers;
    }

    /**
     * @param layers the layers to set
     */
    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }

    public String afficherPixel(int i, int j) {
	for (Layer layer : layers) {
            int color = layer.getPixels().get(i * 25 + j);
            if (color != 2) {
                return color == 0 ? " " : "#";
            }
        }
	return " ";
    }
}
