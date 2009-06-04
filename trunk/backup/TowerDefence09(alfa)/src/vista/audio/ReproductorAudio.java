package vista.audio;

import java.io.File;

import javax.sound.midi.*;

/**
 * Esta clase se encarga del manejo del sonido
 * @author exus
 *
 */

public class ReproductorAudio {

	public static Sequence CANCION_INTRO;
    {
            try {
                    CANCION_INTRO = MidiSystem.getSequence(new File("audio/zelda.mid"));
            } catch (Exception e){
                    e.printStackTrace();
            }
    }
    
    private static ReproductorAudio instancia;
    
    private Sequencer reproductor;
    
    public static ReproductorAudio getInstancia(){
            if (instancia == null){
            	instancia = new ReproductorAudio();
            }
            return instancia;
    }
    
    private ReproductorAudio(){
            try {
                    reproductor = MidiSystem.getSequencer();
                    reproductor.open();
            } catch (MidiUnavailableException e) {
            }
    }
    
    /**
     * Reproduce una secuencia MIDI y la loopea interminablemente
     * @param cancion
     */
    public void reproducirCancion(Sequence cancion){
            try {
                    reproductor.setSequence(cancion);
            } catch (InvalidMidiDataException e) {
            }
            reproductor.start();
            reproductor.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
    }

	
}
