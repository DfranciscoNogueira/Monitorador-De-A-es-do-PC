import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;

/**
 * 
 * @author Diego_Francisco
 *
 */
public class Principal {

    private static String diretorioUsuario = FileSystemView.getFileSystemView().getHomeDirectory().getPath();

    private final static long SEGUNDOS = (1000 * 5);

    public static void main(String[] args) {
        executarPrint();
    }

    /*
     * metodo responsavel por executar o print a cada 5 segundos
     */
    private static void executarPrint() {
        Random random = new Random();
        random.nextInt();

        Timer timer = new Timer();

        TimerTask tarefa = new TimerTask() {

            @Override
            public void run() {
                try {
                    dispararPrint(diretorioUsuario + File.separator + random.nextInt() + ".jpg");
                } catch (AWTException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(tarefa, 0, SEGUNDOS);
    }

    /*
     * metodo responsavel por printar a tela de acordo com o tamanho da tela
     */
    private static void dispararPrint(String caminhoDoPrint) throws AWTException, IOException {

        Toolkit toolkit = Toolkit.getDefaultToolkit();

        Dimension tamanhoTela = toolkit.getScreenSize();

        Rectangle limeteTela = new Rectangle(tamanhoTela);

        Robot robot = new Robot();

        BufferedImage capturaTela = robot.createScreenCapture(limeteTela);

        ImageIO.write(capturaTela, "jpg", new File(caminhoDoPrint));

    }

}
