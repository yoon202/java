package Problem14;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private String[] titles = {
            "audio\\wolf.wav",
            "audio\\hold_drums.wav",
            "audio\\sirenpolice.wav",
            "audio\\hiphop.wav"
    };

    private JCheckBox[] checks = new JCheckBox[titles.length];

    private JButton startBtn = new JButton("연주시작");
    private JButton stopBtn  = new JButton("연주 끝");

    private Clip clip;              // 현재 재생 중인 클립
    private int currentIndex = -1;  // 지금 재생 중인 곡 인덱스
    private boolean playing = false;

    public AudioPlayFrame() {
        setTitle("오디오 연주");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        // 상단 안내 라벨
        JLabel info = new JLabel("체크된 곡만 순서대로 한 번 연주합니다.", JLabel.CENTER);
        c.add(info, BorderLayout.NORTH);

        // 가운데 : 체크박스들
        JPanel center = new JPanel(new GridLayout(0, 1));
        for (int i = 0; i < titles.length; i++) {
            checks[i] = new JCheckBox(titles[i]);
            center.add(checks[i]);
        }
        c.add(new JScrollPane(center), BorderLayout.CENTER);

        // 아래 : 버튼들
        JPanel south = new JPanel();
        south.add(startBtn);
        south.add(stopBtn);
        c.add(south, BorderLayout.SOUTH);

        // 버튼 이벤트
        startBtn.addActionListener(e -> startPlay());
        stopBtn.addActionListener(e -> stopPlay());

        setSize(350, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ───────────────── start/stop 로직 ─────────────────

    // 처음부터 다시 재생 시작
    private void startPlay() {
        stopPlay();          // 혹시 재생 중이면 먼저 정지
        currentIndex = -1;   // 처음 이전 위치에서 시작
        playing = true;
        playNextSelected();  // 다음 체크된 곡 찾기
    }

    // 현재 재생 중인 곡 즉시 정지
    private void stopPlay() {
        playing = false;     // STOP 이벤트에서 다음 곡으로 넘어가지 않게 함
        if (clip != null) {
            clip.stop();
            clip.close();
            clip = null;
        }
        resetHighlight();
        currentIndex = -1;
    }

    // 현재 인덱스 이후에서 다음 체크된 곡을 찾아 재생
    private void playNextSelected() {
        int i = currentIndex;
        while (true) {
            i++;
            if (i >= checks.length) { // 더 이상 재생할 곡 없음
                playing = false;
                resetHighlight();
                return;
            }
            if (checks[i].isSelected()) {
                currentIndex = i;
                playCurrent();
                return;
            }
        }
    }

    // currentIndex 곡을 실제로 재생
    private void playCurrent() {
        resetHighlight();
        if (currentIndex < 0 || currentIndex >= titles.length) return;

        try {
            File audioFile = new File(titles[currentIndex]);
            AudioInputStream ais = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(ais);

            // 라인 리스너 : 곡이 끝나면 자동으로 다음 곡 연주
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                        if (playing) {           // 자연 종료인 경우에만 다음 곡
                            playNextSelected();
                        }
                    }
                }
            });

            // 지금 곡 체크박스 강조(글자색 변경)
            checks[currentIndex].setForeground(Color.BLUE);

            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "오디오 파일을 재생할 수 없습니다:\n" + titles[currentIndex],
                    "오류", JOptionPane.ERROR_MESSAGE);
            playNextSelected(); // 문제 있으면 다음 곡으로
        }
    }

    // 강조 색 초기화
    private void resetHighlight() {
        for (JCheckBox cb : checks) {
            cb.setForeground(Color.BLACK);
        }
    }

    public static void main(String[] args) {
        new AudioPlayFrame();
    }
}
