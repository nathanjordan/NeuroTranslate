package unr.neurotranslate.ncsclasses;

public class Arrays {
	
	public int LayerCount;
	public int CellCount;
	public int ClusterCount;
	public int CmpCount;
	public int ChanCount;
	public Brain brain;
	public int nCsh;
	public CShell[] cshell;
	public int nColumns;
	public Column[] columns;
	public int nLsh;
	public LShell[] shells;
	public int nLayers;
	public Layer[] layers;
	public int nCells;
	public Cell[] cells;
	public int nCmp;
	public Compartment[] compartments;
	public int nChannel;
	public Channel[] channels;
	public int nSynapse;
	public Synapse[] synapses;
	///Number of Synaptic learning objects
	public int nSynLearn;
	///Array of pointers to synaptic learning objects
	public SynapticLearner[] learners;
	///Number of synaptic facil/depr objects
	public int nSynFD;            /* Was RSE */
	///Array of pointers to synaptic facil/depr objects
	public SynapticFD[] synapticFDs;
	///Number of synaptic waveforms
	public int nSynPSG; 
	///Array of pointers to synaptic waveform objects
	public SynapticWaveform[] waveforms;
	///number of synapse data objects
	public int nSynData;
	///Array of pointers to synaptic data objects
	public SynapseData[] synapseData;
	///number of synapse augmentation objects
	public int nSynAugmentation;
	///Array of pointers to synaptic augmentation objects
	public SynapticAugmentation[] synapticAugmentations;
	///number of spike shapes
	public int nSpike;
	///Array of pointers to spike shape objects
	public Spike[] spikes;
	///Number of stimulus
	public int nStimulus;
	///Array of pointers to stimulus objects
	public Stimulus[] stimuli;
	///number of stimulus injects
	public int nStInject;
	///Array of pointers to stim inject objects
	public StimulusInject[] stimulusInjects;
	///number of reports
	public int nReports;
	///Array of pointers to report objects
	public Report[] reports;
	///The number of Event objects declared in the input
	public int nEvents;
	///Array of Event objects
	public Event[] events;
	}
