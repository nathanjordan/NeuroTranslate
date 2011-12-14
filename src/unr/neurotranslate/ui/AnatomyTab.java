package unr.neurotranslate.ui;

import org.gnome.gtk.Alignment;
import org.gnome.gtk.Button;
import org.gnome.gtk.HBox;
import org.gnome.gtk.IconSize;
import org.gnome.gtk.Image;
import org.gnome.gtk.Label;
import org.gnome.gtk.Notebook;
import org.gnome.gtk.Stock;
import org.gnome.gtk.VBox;

public class AnatomyTab {
		
	public AnatomyTab( VBox anatomyBox ) {

		// Each nested tab's top level container will be a VBox
		VBox colShellBox = new VBox( false, 5 );
		VBox colBox = new VBox( false, 5 );
		VBox layShellBox = new VBox( false, 5 );
		VBox layBox = new VBox( false, 5 );
		VBox cellBox = new VBox( false, 5 );
		VBox compBox = new VBox( false, 5 );
		
		// Created nested tabs for anatomy tab
		Notebook anatomyTabs = new Notebook();
		anatomyTabs.appendPage( colShellBox, new Label( "Column Shells")  );
		anatomyTabs.appendPage( colBox, new Label( "Columns" ) );
		anatomyTabs.appendPage( layShellBox, new Label( "Layer Shells" ) );
		anatomyTabs.appendPage( layBox, new Label( "Layers" ) );
		anatomyTabs.appendPage( cellBox, new Label( "Cells" ) );
		anatomyTabs.appendPage( compBox, new Label( "Compartments" ) );				
		anatomyTabs.setBorderWidth( 5);

		// Add nested tabs to top level container (anatomy box)
		anatomyBox.packStart( anatomyTabs, false, false, 0 );		
			
		@SuppressWarnings("unused")
		AntColumnShellTab columnShellTab = new AntColumnShellTab( colShellBox );	
							
		@SuppressWarnings("unused")
		AntColumnTab columnTab = new AntColumnTab( colBox );		
		
		@SuppressWarnings("unused")
		AntLayerShellTab layerShellTab = new AntLayerShellTab( layShellBox );		
		
		@SuppressWarnings("unused")
		AntLayerTab layerTab = new AntLayerTab( layBox );
	
		@SuppressWarnings("unused")
		AntCellTab cellTab = new AntCellTab( cellBox );
		
		@SuppressWarnings("unused")
		AntCompartmentTab compartmentTab = new AntCompartmentTab( compBox );
		
		// Add save/reset buttons to anatomy tab
		Alignment balign = new  Alignment( 0.0f, 0.0f, 0.0f, 0.0f );
		Button saveB = new Button( );
		Image save = new Image();
		save.setImage(Stock.SAVE, IconSize.DND);
		saveB.setImage(save);
		Button resetB = new Button();
		Image reset = new Image();
		reset.setImage( Stock.REVERT_TO_SAVED, IconSize.DND );
		resetB.setImage( reset );
		HBox contentControl = new HBox( false, 5 );
		contentControl.packStart( saveB, false, false, 0 );
		contentControl.packStart( resetB, false, false, 5 );
		balign.add(contentControl);		
		
		// pack save and reset button for anatomy tab		
		anatomyBox.packEnd( balign, false, false, 0 );
		
	}
}
