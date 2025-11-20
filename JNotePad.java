package swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class JNotePad extends JFrame {
	
    private JTextPane _textPane;
    private ActionMap _actionMap;
    private boolean _isSaved = true;
    private JFileChooser _fc = new JFileChooser(".");
    private File _file = null;

    public JNotePad() {
        super("JNotePad");
        _textPane = new JTextPane();
        _textPane.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { _isSaved = false; }
            public void removeUpdate(DocumentEvent e) { _isSaved = false; }
            public void changedUpdate(DocumentEvent e) { _isSaved = false; }
        });
        JScrollPane p = new JScrollPane(_textPane);
        add(p);
        _actionMap = createActionMap();
        setJMenuBar(createMenuBar());
        add(createToolBar(), BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menubar = new JMenuBar();
        JMenu m = new JMenu("파일");
        m.add(new JMenuItem(_actionMap.get("새파일")));
        m.add(new JMenuItem(_actionMap.get("열기")));
        m.add(new JMenuItem(_actionMap.get("저장")));
        m.add(new JMenuItem(_actionMap.get("다른이름으로 저장")));
        m.addSeparator();
        m.add(new JMenuItem(_actionMap.get("종료")));
        menubar.add(m);

        m = new JMenu("수정");
        m.add(new JMenuItem(_actionMap.get("잘라내기")));
        m.add(new JMenuItem(_actionMap.get("복사")));
        m.add(new JMenuItem(_actionMap.get("붙여넣기")));
        m.addSeparator();
        m.add(new JMenuItem(_actionMap.get("찾기")));
        m.add(new JMenuItem(_actionMap.get("바꾸기")));
        menubar.add(m);

        m = new JMenu("도움말");
        m.add(new JMenuItem(_actionMap.get("도움말")));
        m.add(new JMenuItem(_actionMap.get("정보")));
        menubar.add(m);

        return menubar;
    }

    private JToolBar createToolBar() {
        JToolBar toolbar = new JToolBar();
        toolbar.add(new JButton(_actionMap.get("새파일")));
        toolbar.add(new JButton(_actionMap.get("열기")));
        toolbar.add(new JButton(_actionMap.get("저장")));
        toolbar.add(new JButton(_actionMap.get("다른이름으로 저장")));
        toolbar.addSeparator();
        toolbar.add(new JButton(_actionMap.get("복사")));
        toolbar.add(new JButton(_actionMap.get("잘라내기")));
        toolbar.add(new JButton(_actionMap.get("붙여넣기")));
        toolbar.addSeparator();
        toolbar.add(new JButton(_actionMap.get("찾기")));
        toolbar.add(new JButton(_actionMap.get("바꾸기")));
        toolbar.addSeparator();
        toolbar.add(new JButton(_actionMap.get("도움말")));
        toolbar.add(new JButton(_actionMap.get("정보")));

        for (Component c : toolbar.getComponents()) {
            if (c instanceof JButton) c.setFocusable(false);
        }

        return toolbar;
    }

    // =================== 액션 클래스 ===================
    private class NewAction extends AbstractAction {
        public NewAction() {
            super("새파일");
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("Ctrl+N"));
            putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);
        }
        public void actionPerformed(ActionEvent e) {
            if(!confirmSave()) return;
            _textPane.setText("");
            _isSaved = true;
            _file = null;
            setTitle("JNotePad");
        }
    }

    private class OpenAction extends AbstractAction {
        public OpenAction() {
            super("열기");
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("Ctrl+O"));
            putValue(Action.MNEMONIC_KEY, KeyEvent.VK_O);
        }
        public void actionPerformed(ActionEvent e) {
            if(!confirmSave()) return;
            _isSaved = open();
        }
    }

    private class SaveAction extends AbstractAction {
        public SaveAction() {
            super("저장");
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("Ctrl+S"));
            putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
        }
        public void actionPerformed(ActionEvent e) {
            _isSaved = save();
        }
    }

    private class SaveAsAction extends AbstractAction {
        public SaveAsAction() {
            super("다른이름으로 저장");
        }
        public void actionPerformed(ActionEvent e) {
            _isSaved = saveAs();
        }
    }

    private class ExitAction extends AbstractAction {
        public ExitAction() { super("종료"); }
        public void actionPerformed(ActionEvent e) {
            if(!confirmSave()) return;
            System.exit(0);
        }
    }

    private class CutAction extends AbstractAction {
        public CutAction() { super("잘라내기"); }
        public void actionPerformed(ActionEvent e) { _textPane.cut(); }
    }

    private class CopyAction extends AbstractAction {
        public CopyAction() { super("복사"); }
        public void actionPerformed(ActionEvent e) { _textPane.copy(); }
    }

    private class PasteAction extends AbstractAction {
        public PasteAction() { super("붙여넣기"); }
        public void actionPerformed(ActionEvent e) { _textPane.paste(); }
    }

    private class AboutAction extends AbstractAction {
        public AboutAction() { super("정보"); }
        public void actionPerformed(ActionEvent e) {
            String[] mesg = { "텍스트 에디터 v1.1", "제작자: 김채훈" };
            JOptionPane.showMessageDialog(JNotePad.this, mesg, "정보", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class HelpAction extends AbstractAction {
        public HelpAction() { super("도움말"); }
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(JNotePad.this, "아직 지원하지 않습니다.", "도움말", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // =================== 찾기/바꾸기 ===================
    private class FindReplaceDialog extends JDialog {
        private JTextField findField, replaceField;
        private JButton findNextButton, replaceButton, replaceAllButton, closeButton;

        public FindReplaceDialog(JFrame parent) {
            super(parent, "찾기/바꾸기", false);
            setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

            JPanel findPanel = new JPanel();
            findPanel.add(new JLabel("찾기:"));
            findField = new JTextField(15);
            findPanel.add(findField);

            JPanel replacePanel = new JPanel();
            replacePanel.add(new JLabel("바꾸기:"));
            replaceField = new JTextField(15);
            replacePanel.add(replaceField);

            JPanel buttonPanel = new JPanel();
            findNextButton = new JButton("다음 찾기");
            replaceButton = new JButton("바꾸기");
            replaceAllButton = new JButton("모두 바꾸기");
            closeButton = new JButton("닫기");
            buttonPanel.add(findNextButton);
            buttonPanel.add(replaceButton);
            buttonPanel.add(replaceAllButton);
            buttonPanel.add(closeButton);

            add(findPanel);
            add(replacePanel);
            add(buttonPanel);
            pack();
            setLocationRelativeTo(parent);

            findNextButton.addActionListener(e -> findNext());
            replaceButton.addActionListener(e -> replace());
            replaceAllButton.addActionListener(e -> replaceAll());
            closeButton.addActionListener(e -> setVisible(false));
        }

        private void findNext() {
            String text = _textPane.getText();
            String find = findField.getText();
            if(find.isEmpty()) return;

            int pos = _textPane.getCaretPosition();
            int index = text.indexOf(find, pos);

            if(index == -1 && pos > 0) index = text.indexOf(find);
            if(index != -1) {
                _textPane.requestFocus();
                _textPane.select(index, index + find.length());
            } else {
                JOptionPane.showMessageDialog(this, "찾는 문자열이 없습니다.", "찾기", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        private void replace() {
            String selected = _textPane.getSelectedText();
            if(selected != null && selected.equals(findField.getText())) {
                _textPane.replaceSelection(replaceField.getText());
                _isSaved = false;
            }
            findNext();
        }

        private void replaceAll() {
            String text = _textPane.getText();
            text = text.replace(findField.getText(), replaceField.getText());
            _textPane.setText(text);
            _isSaved = false;
        }
    }

    private class FindAction extends AbstractAction {
        public FindAction() { super("찾기"); putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("Ctrl+F")); }
        public void actionPerformed(ActionEvent e) { new FindReplaceDialog(JNotePad.this).setVisible(true); }
    }

    private class ReplaceAction extends AbstractAction {
        public ReplaceAction() { super("바꾸기"); putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("Ctrl+R")); }
        public void actionPerformed(ActionEvent e) { new FindReplaceDialog(JNotePad.this).setVisible(true); }
    }

    // =================== 파일 작업 ===================
    private boolean confirmSave() {
        if(_isSaved) return true;
        int ret = JOptionPane.showConfirmDialog(this, "내용이 수정되었습니다. 저장하시겠습니까?", "텍스트 에디터", JOptionPane.YES_NO_CANCEL_OPTION);
        if(ret == JOptionPane.YES_OPTION) return save();
        else return ret == JOptionPane.NO_OPTION;
    }

    private boolean open() {
        if(_fc.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) return false;
        File file = _fc.getSelectedFile();
        try {
            BufferedReader r = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            char[] buf = new char[1024];
            int n;
            while((n=r.read(buf)) != -1) sb.append(buf,0,n);
            r.close();
            _textPane.setText(sb.toString());
            _file = file;
            setTitle(file.getName() + " - JNotePad");
            return true;
        } catch(IOException e) {
            JOptionPane.showMessageDialog(this, "파일을 열 수 없습니다: " + file, "오류", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean save() {
        if(_file == null) return saveAs();
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter(_file));
            w.write(_textPane.getText());
            w.close();
            _isSaved = true;
            return true;
        } catch(IOException e) {
            JOptionPane.showMessageDialog(this, "저장 실패: " + _file, "오류", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean saveAs() {
        if(_fc.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return false;
        _file = _fc.getSelectedFile();
        return save();
    }

    private ActionMap createActionMap() {
        ActionMap am = new ActionMap();
        am.put("새파일", new NewAction());
        am.put("열기", new OpenAction());
        am.put("저장", new SaveAction());
        am.put("다른이름으로 저장", new SaveAsAction());
        am.put("종료", new ExitAction());
        am.put("잘라내기", new CutAction());
        am.put("복사", new CopyAction());
        am.put("붙여넣기", new PasteAction());
        am.put("정보", new AboutAction());
        am.put("도움말", new HelpAction());
        am.put("찾기", new FindAction());
        am.put("바꾸기", new ReplaceAction());
        return am;
    }

    private void start() {
        setSize(700,480);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new JNotePad().start();
    }
}