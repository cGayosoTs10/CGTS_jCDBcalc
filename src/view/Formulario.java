package view;

import business.Aplicacao;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Formulario {

    private JFrame form;
    private JLabel lblValor, lblPrazo, lblTipo, lblResultado;
    private JTextField txtValor, txtPrazo;
    private JComboBox<String> cbxInvestimento;
    private JButton btnCalcular;

    public Formulario() {
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        form = new JFrame("Calculadora de Investimento");
        form.setBounds(500, 200, 450, 320);
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setLayout(null);

        Container painel = form.getContentPane();

        lblValor = new JLabel("Valor a aplicar (R$):");
        lblValor.setBounds(30, 30, 150, 25);

        lblPrazo = new JLabel("Prazo (meses):");
        lblPrazo.setBounds(30, 70, 150, 25);

        lblTipo = new JLabel("Tipo de Taxa:");
        lblTipo.setBounds(30, 110, 150, 25);

        lblResultado = new JLabel("Resultado: R$ 0.00");
        lblResultado.setBounds(30, 200, 350, 25);

        txtValor = new JTextField();
        txtValor.setBounds(180, 30, 150, 25);
        txtValor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '.') {
                    e.consume();
                }
            }
        });

        txtPrazo = new JTextField();
        txtPrazo.setBounds(180, 70, 150, 25);
        txtPrazo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });

        String[] opcoes = { "Poupança", "CDI", "Tesouro Direto" };
        cbxInvestimento = new JComboBox<>(opcoes);
        cbxInvestimento.setBounds(180, 110, 150, 25);

        btnCalcular = new JButton("Calcular Rendimento");
        btnCalcular.setBounds(30, 155, 300, 30);
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    float valor = Float.parseFloat(txtValor.getText());
                    int prazo = Integer.parseInt(txtPrazo.getText());

                    if (valor <= 0 || prazo <= 0) {
                        throw new IllegalArgumentException("O valor e o prazo devem ser maiores que zero!");
                    }

                    float taxa = 0.0f;
                    int index = cbxInvestimento.getSelectedIndex();

                    if (index == 0) {
                        taxa = 0.38f;
                    } else if (index == 1) {
                        taxa = 0.53f;
                    } else if (index == 2) {
                        taxa = 0.65f;
                    }

                    Aplicacao app = new Aplicacao();
                    app.calcularRendimento(valor, prazo, taxa);

                    lblResultado.setText(String.format("Resultado: R$ %.2f", app.getMontante()));

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                        form, 
                        "Por favor, preencha todos os campos com números válidos!", 
                        "Erro de Entrada", 
                        JOptionPane.ERROR_MESSAGE
                    );
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(
                        form, 
                        ex.getMessage(), 
                        "Aviso", 
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });

        painel.add(lblValor);
        painel.add(lblPrazo);
        painel.add(lblTipo);
        painel.add(lblResultado);

        painel.add(txtValor);
        painel.add(txtPrazo);
        painel.add(cbxInvestimento);
        painel.add(btnCalcular);

        form.setVisible(true);
    }
}