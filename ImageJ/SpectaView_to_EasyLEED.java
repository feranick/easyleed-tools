/**	Specta_to_EasyLEED conversion utility for ImageJ */
/**	Nicola Ferralis - ferralis@mit.edu */
/**	v.1.0-20141230 */
/** License: Public Domain */

import ij.plugin.*;
import ij.*;
import ij.io.*;
import java.io.*;
import ij.gui.*;
import ij.process.*;
import java.awt.*;

import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;
import ij.gui.*;
import ij.measure.Calibration;
import java.awt.*;
import ij.plugin.*;


/**	This plugins open SpectaView LEED Files to be processed in EasyLEED */
public class SpectaView_to_EasyLEED  implements PlugIn {
	static File dir;
    
    private static double initEn = 6;
    private static double endEn = 250;
    private static double stepEn = 1;
    private static int numImages = 10;

    ImagePlus imp;
    
	public void run(String arg) {
        if (openDialog())
            {if (!IJ.versionLessThan("1.40e"))
                openFiles();}
		IJ.register( SpectaView_to_EasyLEED.class);
	}
    

	public void openFiles() {
        IJ.run("Raw...", "image=[16-bit Unsigned] width=512 height=512 offset=1000 number=" + numImages + " gap=0 little-endian");
        IJ.run("8-bit");
        IJ.showStatus("Starting Energy: " + initEn + "eV; End Energy: " + endEn + "eV; Energy Step: " + stepEn + "eV");
        imp = IJ.getImage();
        if(imp.isVisible()) {
            if (saveDialog())
                {IJ.run("Image Sequence... ", "format=TIFF name=" + imp.getTitle() + "_ start=6 digits=3 save");}
        }
	}
    
    public boolean openDialog() {
        GenericDialog gd = new GenericDialog("Open...");
        gd.addMessage("SpectaView LEED parameters\n");
        
        gd.addNumericField("Initial Energy: ", initEn, 0);
        gd.addNumericField("Final Energy: ", endEn, 0);
        gd.addNumericField("Energy step: ", stepEn, 0);
        gd.addMessage("\n_____________________________________");
        gd.addMessage("\nSpectaView to easyLEED converter...\n" + "version: 1.0-20141230\n" +
               "Nicola Ferralis - ferralis@mit.edu");
        gd.showDialog();
        if (gd.wasCanceled()) {return false;}
        initEn = gd.getNextNumber();
        endEn = gd.getNextNumber();
        stepEn = gd.getNextNumber();
        numImages = (int)((endEn-initEn)/stepEn);
        //IJ.error("numImages: " + numImages + " " + initEn + " " + endEn + " " + stepEn);
        return true;
    }
    
    public boolean saveDialog() {
        GenericDialog gd = new GenericDialog("Save...");
        gd.addMessage("Save Image Sequence for EasyLEED?");
        gd.showDialog();
        if (gd.wasCanceled()) {return false;}
        initEn = gd.getNextNumber();
        endEn = gd.getNextNumber();
        stepEn = gd.getNextNumber();
        numImages = (int)((endEn-initEn)/stepEn);
        return true;
    }
}
