package unr.neurotranslate.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.morphml.channelml.schema.DoubleExponentialSynapse;
import org.morphml.channelml.schema.SynapseType;
import org.morphml.morphml.schema.Cable;
import org.morphml.morphml.schema.Cell;
import org.morphml.morphml.schema.Segment;
import org.morphml.morphml.schema.Cell.Segments;
import org.morphml.networkml.schema.Input;
import org.morphml.networkml.schema.Population;
import org.morphml.networkml.schema.Projection;
import org.morphml.neuroml.schema.Level3Cell;
import org.morphml.neuroml.schema.Neuroml;
import unr.neurotranslate.model.Data;
import unr.neurotranslate.neuromlparser.NeuroMLConverter;

public class UIControllerNeuroML {

private Data _data;
	
	public UIControllerNeuroML() {
		
		NeuroMLConverter n = null;
		try {
			n = new NeuroMLConverter();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Neuroml d = null;
		try {
			d = n.readNeuroML( "/home/nitishn/workspace/NeuroTranslate/samples/PrototypeNeuroml.xml" );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		_data = Data.getInstance();
		
		_data.nml = d;
		
		}

	///////////////////////////////////////////////////////////////////
	////  Cells
	///////////////////////////////////////////////////////////////////

	public ArrayList<String> getCells() {
		
		ArrayList<String> l = new ArrayList<String>();
		
		List<Level3Cell> list = _data.nml.getCells().getCells();
		
		for( Level3Cell c : list ) {
			
			if( c.getName() != null && !(c.getName().equals("")) )
				
				l.add(c.getName());
			
			else
				
				l.add("Cell1");
			
			}
		
		return l;
		
		}
	
	public Level3Cell getCellByName( String name ) throws Exception {
		
		List<Level3Cell> list = _data.nml.getCells().getCells();
		
		for( Level3Cell c : list ) {
					
			if( c.getName() != null && c.getName().equals(name) )
				
				return c;
				
				
			}
		
		throw new Exception("Cell doesn't exist!");
		
		}
	
	public Level3Cell addCell() {
		
		Level3Cell c = new Level3Cell();
		
		c.setName("Cell1");
		
		_data.nml.getCells().getCells().add( c );
		
		return c;
		
		}
	
	public void removeCell( String name ) throws Exception {
		
		List<Level3Cell> list = _data.nml.getCells().getCells();
		
		for( Level3Cell c : list ) {
					
			if( c.getName() != null && c.getName().equals(name) )
				
				list.remove(c);
				
			}
		
		throw new Exception("Cell doesn't exist!");
		
		}
	
	///////////////////////////////////////////////////////////////////
	////  Populations
	///////////////////////////////////////////////////////////////////

	public ArrayList<String> getPopulations() {
		
		ArrayList<String> l = new ArrayList<String>();
		
		List<Population> list = _data.nml.getPopulations().getPopulations();
		
		for( Population c : list ) {
			
			if( c.getName() != null && c.getName().equals("") )
				
				l.add(c.getName());
			
			else
				
				l.add("Population1");
			
			}
		
		return l;
		
		}
	
	public Population getPopulationByName( String name ) throws Exception {
		
		List<Population> list = _data.nml.getPopulations().getPopulations();
		
		for( Population p : list ) {
					
			if( p.getName() != null && p.getName().equals(name) )
				
				return p;
				
				
			}
		
		throw new Exception("Cell doesn't exist!");
		
		}
	
	public Population addCable() {
		
		Population c = new Population();
		
		c.setName("Population1");
		
		_data.nml.getPopulations().getPopulations().add( c );
		
		return c;
		
		}
	
	public void removePopulation( String name ) throws Exception {
		
		List<Population> list = _data.nml.getPopulations().getPopulations();
		
		for( Population c : list ) {
					
			if( c.getName() != null && c.getName().equals(name) )
				
				list.remove(c);
				
			}
		
		throw new Exception("Cell doesn't exist!");
		
		}
	
	///////////////////////////////////////////////////////////////////
	////  Projections
	///////////////////////////////////////////////////////////////////

	public ArrayList<String> getProjections() {
		
		ArrayList<String> l = new ArrayList<String>();
		
		List<Projection> list = _data.nml.getProjections().getProjections();
		
		for( Projection c : list ) {
			
			if( c.getName() != null && c.getName().equals("") )
				
				l.add(c.getName());
			
			else
				
				l.add("Projection1");
			
			}
		
		return l;
		
		}
	
	public Projection getProjectionByName( String name ) throws Exception {
		
		List<Projection> list = _data.nml.getProjections().getProjections();
		
		for( Projection p : list ) {
					
			if( p.getName() != null && p.getName().equals(name) )
				
				return p;
				
				
			}
		
		throw new Exception("Projection doesn't exist!");
		
		}
	
	public Projection addProjection() {
		
		Projection c = new Projection();
		
		c.setName("Projection1");
		
		_data.nml.getProjections().getProjections().add( c );
		
		return c;
		
		}
	
	public void removeProjection( String name ) throws Exception {
		
		List<Projection> list = _data.nml.getProjections().getProjections();
		
		for( Projection c : list ) {
					
			if( c.getName() != null && c.getName().equals(name) )
				
				list.remove(c);
				
			}
		
		throw new Exception("Projection doesn't exist!");
		
		}
	
	///////////////////////////////////////////////////////////////////
	////  Projections
	///////////////////////////////////////////////////////////////////

	public ArrayList<String> getInputs() {
		
		ArrayList<String> l = new ArrayList<String>();
		
		List<Input> list = _data.nml.getInputs().getInputs();
		
		for( Input c : list ) {
			
			if( c.getName() != null && c.getName().equals("") )
				
				l.add(c.getName());
			
			else
				
				l.add("Input1");
			
			}
		
		return l;
		
		}
	
	public Input getInputByName( String name ) throws Exception {
		
		List<Input> list = _data.nml.getInputs().getInputs();
		
		for( Input p : list ) {
					
			if( p.getName() != null && p.getName().equals(name) )
				
				return p;
				
				
			}
		
		throw new Exception("Input doesn't exist!");
		
		}
	
	public Input addInput() {
		
		Input c = new Input();
		
		c.setName("Input1");
		
		_data.nml.getInputs().getInputs().add( c );
		
		return c;
		
		}
	
	public void removeInput( String name ) throws Exception {
		
		List<Input> list = _data.nml.getInputs().getInputs();
		
		for( Input c : list ) {
					
			if( c.getName() != null && c.getName().equals(name) )
				
				list.remove(c);
				
			}
		
		throw new Exception("Input doesn't exist!");
		
		}
	
	///////////////////////////////////////////////////////////////////
	////  Cables
	///////////////////////////////////////////////////////////////////

	public ArrayList<String> getCables( Cell c) {
		
		ArrayList<String> l = new ArrayList<String>();
		
		if( c.getCables() != null ) {
			List<Cable> list = c.getCables().getCables();
			
			for( Cable c1 : list ) {
				
				if( c1.getName() != null && c1.getName().equals("") )
					
					l.add(c1.getName());
				
				else
					
					l.add("Cable1");
				
				}
			
			return l;	
		}
		
		return l;
		}
	
	public Cable getCableByName( Cell c , String name ) throws Exception {
		
		List<Cable> list = c.getCables().getCables();
		
		for( Cable c1 : list ) {
					
			if( c1.getName() != null && c1.getName().equals(name) )
				
				return c1;
				
				
			}
		
		throw new Exception("Cable doesn't exist!");
		
		}
	
	public Cable addCable( Cell c ) {
		
		Cable c1 = new Cable();
		
		c1.setName("Cable1");
		
		c.getCables().getCables().add( c1 );
		
		return c1;
		
		}
	
	public void removeCable( Cell c , String name ) throws Exception {
		
		List<Cable> list = c.getCables().getCables();
		
		for( Cable c1 : list ) {
					
			if( c1.getName() != null && c1.getName().equals(name) )
				
				list.remove(c1);
				
			}
		
		throw new Exception("Cable doesn't exist!");
		
		}
	
	///////////////////////////////////////////////////////////////////
	////  Segments
	///////////////////////////////////////////////////////////////////

	public ArrayList<String> getSegments( Cell c) {
		
		ArrayList<String> l = new ArrayList<String>();
		
		if( c.getSegments() != null ) {
			
			List<Segment> list = c.getSegments().getSegments();
			
			for( Segment c1 : list ) {
				
				if( c1.getName() != null && !(c1.getName().equals("")) )
					
					l.add(c1.getName());
				
				else
					
					l.add("Segment1");
				
				}
			
			return l;	
		}
		
			return l;
		}
	
	public Segment getSegmentByName( Cell c , String name ) throws Exception {
		
		List<Segment> list = c.getSegments().getSegments();
		
		for( Segment c1 : list ) {
					
			if( c1.getName() != null && c1.getName().equals(name) )
				
				return c1;
				
				
			}
		
		throw new Exception("Segment doesn't exist!");
		
		}
	
	public Segment addSegment( Cell c ) {
		
		Segment c1 = new Segment();
		
		c1.setName("Segment1");		
		
		if( c.getSegments() == null ) {
			c.setSegments(new Segments() );			
		}				
			
		c.getSegments().getSegments().add( c1 );
		
		return c1;
		
		}
	
	public void removeSegment( Cell c , String name ) throws Exception {
		
		List<Segment> list = c.getSegments().getSegments();
		
		for( Segment c1 : list ) {
					
			if( c1.getName() != null && c1.getName().equals(name) )
				
				list.remove(c1);
				
			}
		
		throw new Exception("Segment doesn't exist!");
		
		}
	
	///////////////////////////////////////////////////////////////////
	////  Synapses
	///////////////////////////////////////////////////////////////////

	public ArrayList<String> getSynapses( ) {
		
		ArrayList<String> l = new ArrayList<String>();
		
		List<SynapseType> list = _data.nml.getChannels().getSynapseTypes();
		
		for( SynapseType c1 : list ) {
			
			if( c1.getDoubExpSyn() != null && c1.getName() != null && c1.getName().equals("") )
				
				l.add(c1.getName());
			
			}
		
		return l;
		
		}
	
	public SynapseType getSynapsebyName( String name ) throws Exception {
		
		List<SynapseType> list = _data.nml.getChannels().getSynapseTypes();
		
		for( SynapseType c1 : list ) {
					
			if( c1.getDoubExpSyn() != null && c1.getName() != null && c1.getName().equals(name) )
				
				return c1;
				
				
			}
		
		throw new Exception("Synapse doesn't exist!");
		
		}
	
	public SynapseType addSynapse() {
		
		DoubleExponentialSynapse c1 = new DoubleExponentialSynapse();
		
		SynapseType s = new SynapseType();
		
		s.setName("Synapse1");
		
		s.setDoubExpSyn(c1);
		
		_data.nml.getChannels().getSynapseTypes().add( s );
		
		return s;
		
		}
	
	public void removeSynapse( String name ) throws Exception {
		
		List<SynapseType> list = _data.nml.getChannels().getSynapseTypes();
		
		for( SynapseType c1 : list ) {
					
			if( c1.getDoubExpSyn() != null && c1.getName() != null && c1.getName().equals(name) )
				
				list.remove( c1 );
				
				
			}
		
		throw new Exception("Synapse doesn't exist!");
		
		}
	
	}
