package swingComponent;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SimplePaint extends JFrame {
    private static final long serialVersionUID = 1L;

    private DrawPanel drawPanel;

    public SimplePaint() {
        setTitle("그림판 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // -------------------------
        //  메뉴바 생성
        // -------------------------
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("파일");
        JMenu editMenu = new JMenu("편집");
        JMenu viewMenu = new JMenu("보기");

        JMenuItem newItem  = new JMenuItem("새 파일");
        JMenuItem openItem = new JMenuItem("열기");
        JMenuItem saveItem = new JMenuItem("저장");
        JMenuItem exitItem = new JMenuItem("종료");

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // ---- 편집 메뉴: 잘라내기 / 복사 / 붙여넣기 / 전체 지우기 ----
        JMenuItem cutItem   = new JMenuItem("잘라내기");
        JMenuItem copyItem  = new JMenuItem("복사");
        JMenuItem pasteItem = new JMenuItem("붙여넣기");
        JMenuItem clearItem = new JMenuItem("전체 지우기");

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.addSeparator();
        editMenu.add(clearItem);

        // ---- 보기 메뉴: 개발자 정보 ----
        JMenuItem toolbarItem = new JMenuItem("도구모음 표시");
        JMenuItem fullscreenItem = new JMenuItem("전체화면");
        JMenuItem aboutItem = new JMenuItem("개발자 정보");

        viewMenu.add(toolbarItem);
        viewMenu.add(fullscreenItem);
        viewMenu.addSeparator();
        viewMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);

        setJMenuBar(menuBar);

        drawPanel = new DrawPanel();

        // -------------------------
        //  파일 메뉴 기능 연결
        // -------------------------
        newItem.addActionListener(e -> drawPanel.newFile());

        openItem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("이미지 열기");
            chooser.setFileFilter(new FileNameExtensionFilter(
                    "이미지 파일 (png, jpg, jpeg, bmp)", "png", "jpg", "jpeg", "bmp"));

            int result = chooser.showOpenDialog(SimplePaint.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try {
                    drawPanel.openImage(file);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(SimplePaint.this,
                            "이미지 파일을 열 수 없습니다.\n" + ex.getMessage(),
                            "오류", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        saveItem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("이미지 저장");
            chooser.setFileFilter(new FileNameExtensionFilter(
                    "PNG 이미지 (*.png)", "png"));

            int result = chooser.showSaveDialog(SimplePaint.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                if (!file.getName().toLowerCase().endsWith(".png")) {
                    file = new File(file.getParentFile(), file.getName() + ".png");
                }
                try {
                    drawPanel.saveImage(file);
                    JOptionPane.showMessageDialog(SimplePaint.this,
                            "저장 완료:\n" + file.getAbsolutePath(),
                            "완료", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(SimplePaint.this,
                            "이미지 파일을 저장할 수 없습니다.\n" + ex.getMessage(),
                            "오류", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        exitItem.addActionListener(e -> System.exit(0));

        // -------------------------
        //  편집 메뉴 기능 연결
        // -------------------------
        cutItem.addActionListener(e -> drawPanel.cutAll());    // 잘라내기
        copyItem.addActionListener(e -> drawPanel.copyAll());  // 복사
        pasteItem.addActionListener(e -> drawPanel.paste());   // 붙여넣기
        clearItem.addActionListener(e -> drawPanel.clear());   // 전체 지우기

        // -------------------------
        //  보기 메뉴 기능 연결
        // -------------------------
        aboutItem.addActionListener(e -> {
            String msg =
                    "개발자 정보\n" +
                    "-------------------------\n" +
                    "이름 : 최민재\n" +
                    "버전 : SimplePaint 1.0\n" +
                    "설명 : Java Swing 연습용 간단 그림판 프로그램";
            JOptionPane.showMessageDialog(SimplePaint.this,
                    msg, "개발자 정보", JOptionPane.INFORMATION_MESSAGE);
        });

        // (toolbarItem, fullscreenItem 은 아직 동작 없음. 원하면 기능도 붙일 수 있음.)

        // -------------------------
        //  색 팔레트 패널
        // -------------------------
        JPanel colorPanel = new JPanel();
        colorPanel.setBackground(new Color(40, 40, 40));
        colorPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        Color[] palette = {
                Color.BLACK, new Color(70,70,70),
                new Color(128,0,0),  new Color(192,0,0),
                new Color(230,126,34), new Color(241,196,15),
                new Color(46,204,113), new Color(41,128,185),
                new Color(52,73,94),  new Color(142,68,173),

                Color.WHITE, new Color(220,220,220),
                new Color(210,180,140), new Color(244,164,96),
                new Color(255,215,0),   new Color(189,195,199),
                new Color(144,238,144), new Color(173,216,230),
                new Color(216,191,216), new Color(255,182,193)
        };

        ButtonGroup colorGroup = new ButtonGroup();

        for (int i = 0; i < palette.length; i++) {
            ColorSwatchButton btn = new ColorSwatchButton(palette[i]);
            if (i == 0) {
                btn.setSelected(true);
                drawPanel.setCurrentColor(palette[i]);
            }
            btn.addActionListener(e -> drawPanel.setCurrentColor(btn.getColor()));
            colorGroup.add(btn);
            colorPanel.add(btn);
        }

        JButton customColorBtn = new JButton("+");
        customColorBtn.setMargin(new Insets(0, 0, 0, 0));
        customColorBtn.setPreferredSize(new Dimension(28, 28));
        customColorBtn.setFocusPainted(false);
        customColorBtn.addActionListener(e -> {
            Color chosen = JColorChooser.showDialog(
                    SimplePaint.this, "색 선택", drawPanel.getCurrentColor());
            if (chosen != null) {
                drawPanel.setCurrentColor(chosen);
            }
        });
        colorPanel.add(customColorBtn);

        add(colorPanel, BorderLayout.NORTH);
        add(drawPanel, BorderLayout.CENTER);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // -------------------------
    //  동그란 색 버튼
    // -------------------------
    class ColorSwatchButton extends JToggleButton {
        private static final long serialVersionUID = 1L;
        private final Color color;

        public ColorSwatchButton(Color color) {
            this.color = color;
            setPreferredSize(new Dimension(24, 24));
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
        }

        public Color getColor() {
            return color;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();
            int diameter = Math.min(w, h) - 4;

            g2.setColor(color);
            g2.fillOval((w - diameter) / 2, (h - diameter) / 2,
                    diameter, diameter);

            if (isSelected()) {
                g2.setStroke(new BasicStroke(2));
                g2.setColor(new Color(100, 160, 255));
                g2.drawOval((w - diameter) / 2 - 2, (h - diameter) / 2 - 2,
                        diameter + 4, diameter + 4);
            } else {
                g2.setColor(Color.DARK_GRAY);
                g2.drawOval((w - diameter) / 2, (h - diameter) / 2,
                        diameter, diameter);
            }

            g2.dispose();
        }
    }

    // --------------------------------
    //             그림판 패널
    // --------------------------------
    class DrawPanel extends JPanel {
        private static final long serialVersionUID = 1L;

        private BufferedImage canvas;
        private Graphics2D canvasGraphics;
        private int prevX, prevY;
        private Color currentColor = Color.BLACK;

        // 클립보드 용 이미지 (잘라내기/복사/붙여넣기)
        private BufferedImage clipboardImage;

        public DrawPanel() {
            setBackground(Color.WHITE);

            MouseAdapter mouseHandler = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    prevX = e.getX();
                    prevY = e.getY();
                    initCanvas();
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    int x = e.getX();
                    int y = e.getY();
                    if (canvasGraphics != null) {
                        canvasGraphics.setColor(currentColor);
                        canvasGraphics.setStroke(new BasicStroke(3));
                        canvasGraphics.drawLine(prevX, prevY, x, y);
                    }
                    prevX = x;
                    prevY = y;
                    repaint();
                }
            };

            addMouseListener(mouseHandler);
            addMouseMotionListener(mouseHandler);

            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    resetCanvasSize();
                }
            });
        }

        // ==== 파일 관련 메서드 ====

        public void newFile() {
            canvas = null;
            if (canvasGraphics != null) {
                canvasGraphics.dispose();
                canvasGraphics = null;
            }
            repaint();
        }

        public void openImage(File file) throws IOException {
            BufferedImage img = ImageIO.read(file);
            if (img == null) {
                throw new IOException("이미지 형식이 아닙니다.");
            }

            canvas = new BufferedImage(getWidth(), getHeight(),
                    BufferedImage.TYPE_INT_ARGB);
            if (canvasGraphics != null) {
                canvasGraphics.dispose();
            }
            canvasGraphics = canvas.createGraphics();
            canvasGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            canvasGraphics.setColor(Color.WHITE);
            canvasGraphics.fillRect(0, 0, getWidth(), getHeight());

            canvasGraphics.drawImage(img, 0, 0, getWidth(), getHeight(), null);
            canvasGraphics.setColor(currentColor);

            repaint();
        }

        public void saveImage(File file) throws IOException {
            if (canvas == null) {
                initCanvas();
            }
            ImageIO.write(canvas, "png", file);
        }

        // ==== 편집(잘라내기/복사/붙여넣기) 관련 메서드 ====

        // 캔버스 전체 복사
        public void copyAll() {
            if (canvas == null) {
                initCanvas();
            }
            if (canvas == null) return;

            clipboardImage = new BufferedImage(
                    canvas.getWidth(), canvas.getHeight(), canvas.getType());
            Graphics2D g2 = clipboardImage.createGraphics();
            g2.drawImage(canvas, 0, 0, null);
            g2.dispose();
        }

        // 캔버스 전체 잘라내기 (복사 후 지우기)
        public void cutAll() {
            copyAll();
            clear();
        }

        // 클립보드 이미지 붙여넣기 (좌측 상단에 붙임)
        public void paste() {
            if (clipboardImage == null) return;
            initCanvas();
            canvasGraphics.drawImage(clipboardImage, 0, 0, null);
            repaint();
        }

        // ==== 기존 그리기 기능 ====

        public void setCurrentColor(Color color) {
            this.currentColor = color;
            if (canvasGraphics != null) {
                canvasGraphics.setColor(color);
            }
        }

        public Color getCurrentColor() {
            return currentColor;
        }

        public void clear() {
            if (canvasGraphics != null) {
                canvasGraphics.setColor(Color.WHITE);
                canvasGraphics.fillRect(0, 0, getWidth(), getHeight());
                canvasGraphics.setColor(currentColor);
                repaint();
            }
        }

        private void initCanvas() {
            if (canvas == null) {
                canvas = new BufferedImage(getWidth(), getHeight(),
                        BufferedImage.TYPE_INT_ARGB);
                if (canvasGraphics != null) {
                    canvasGraphics.dispose();
                }
                canvasGraphics = canvas.createGraphics();
                canvasGraphics.setColor(Color.WHITE);
                canvasGraphics.fillRect(0, 0, getWidth(), getHeight());
                canvasGraphics.setColor(currentColor);

                canvasGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
            }
        }

        private void resetCanvasSize() {
            if (getWidth() <= 0 || getHeight() <= 0) return;

            BufferedImage newCanvas = new BufferedImage(getWidth(), getHeight(),
                    BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = newCanvas.createGraphics();
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, getWidth(), getHeight());

            if (canvas != null) {
                g2.drawImage(canvas, 0, 0, getWidth(), getHeight(), null);
            }

            g2.dispose();

            canvas = newCanvas;
            if (canvasGraphics != null) {
                canvasGraphics.dispose();
            }
            canvasGraphics = canvas.createGraphics();
            canvasGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            canvasGraphics.setColor(currentColor);
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (canvas == null) {
                initCanvas();
            }
            g.drawImage(canvas, 0, 0, null);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimplePaint::new);
    }
}

