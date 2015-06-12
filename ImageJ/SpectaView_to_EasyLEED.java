/**	Specta_to_EasyLEED conversion utility for ImageJ */
/**	Nicola Ferralis - ferralis@mit.edu */
/**	v.1.1-20150612 */
/** License: Public Domain */

import ij.*;
import ij.io.*;
import ij.gui.*;
import ij.plugin.*;

/**	This plugins open Omicron SpectaView LEED Files to be processed in EasyLEED */
public class SpectaView_to_EasyLEED  implements PlugIn {
    
    private static double initEn = 6;
    private static double endEn = 250;
    private static double stepEn = 1;
    private static int numImages = 1;

    ImagePlus imp;
    
	public void run(String arg) {
        IJ.showStatus("SpectaView to EasyLEED converter v.1.1-20150612 (ferralis@mit.edu)");
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
                {IJ.run("Image Sequence... ", "format=TIFF name=" + imp.getTitle() + "_ start=" + initEn + " digits=3 save");
                IJ.showStatus("Done!");}
        }
	}
    
    public boolean openDialog() {
        GenericDialog gd = new GenericDialog("Omicron SpectaView");
        gd.addMessage("LEED parameters\n");
        
        gd.addNumericField("Initial energy (eV): ", initEn, 0);
        gd.addNumericField("Final energy (eV): ", endEn, 0);
        gd.addNumericField("Energy step (eV): ", stepEn, 0);
        gd.showDialog();
        if (gd.wasCanceled()) {return false;}
        initEn = gd.getNextNumber();
        endEn = gd.getNextNumber();
        stepEn = gd.getNextNumber();
        numImages = (int)((endEn-initEn)/stepEn);
        return true;
    }
    
    public boolean saveDialog() {
        GenericDialog gd = new GenericDialog("Save...");
        gd.addMessage("Save Image Sequence for EasyLEED?");
        gd.showDialog();
        if (gd.wasCanceled()) {return false;}
        return true;
    }
}
