package kr.ac.jbnu.se.tetris.views;

import kr.ac.jbnu.se.tetris.models.KeyInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static kr.ac.jbnu.se.tetris.service.HttpService.httpService;

//일반적인 메뉴를 포함한, 게임 페이즐 제외한 페이지
public class Page extends JFrame {
    String choice = null;

    public Page() {

        setTitle("회원관리 화면");

        // 1. 컴포넌트들을 만들어 보자.
        JLabel title =
                new JLabel("회원가입", JLabel.CENTER);

        title.setForeground(new Color(5, 0, 153));
        title.setFont(new Font("휴먼편지체", Font.BOLD, 30));

        JButton join = new JButton("회원가입");
        JButton cancel = new JButton("취소");

        JTextField id = new JTextField(10);
        JPasswordField pwd = new JPasswordField(10);



        // form panel
        JPanel idPanel = new JPanel();
        idPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        idPanel.add(new JLabel("아이디 : "));
        idPanel.add(id);


        JPanel pwdPanel = new JPanel();
        pwdPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pwdPanel.add(new JLabel("비밀번호 : "));
        pwdPanel.add(pwd);



        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 1));
        formPanel.add(idPanel);
        formPanel.add(pwdPanel);


        // radio + form panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout());
        contentPanel.add(formPanel);

        // button panel
        JPanel panel = new JPanel();
        panel.add(join);
        panel.add(cancel);


        add(title, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);


        setBounds(200, 200, 250, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);


        // 이벤트 처리
        join.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String myId = id.getText();
                String myPwd = new String(pwd.getPassword());
                httpService.memberJoin(myId,myPwd);

                JOptionPane.showMessageDialog
                        (null, "아이디 : "+myId+", 비밀번호 : "+myPwd);
            }
        });


        // 취소 버튼을 클릭했을 때 이벤트 처리
        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new LoginScreen();
                dispose();

            }
        });
    }
}

