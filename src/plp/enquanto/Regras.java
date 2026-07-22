package plp.enquanto;

import static java.lang.Integer.parseInt;

import java.util.ArrayList;
import java.util.List;
import plp.enquanto.Linguagem.*;
import plp.enquanto.parser.EnquantoBaseListener;
import plp.enquanto.parser.EnquantoParser.*;

public class Regras extends EnquantoBaseListener {

    private final Leia leia;
    private final Skip skip;
    private final Propriedades valores;

    private Programa programa;

    public Regras() {
        leia = new Leia();
        skip = new Skip();
        valores = new Propriedades();
    }

    public Programa getPrograma() {
        return programa;
    }

    @Override
    public void exitBool(BoolContext ctx) {
        valores.insira(ctx, new Booleano("verdadeiro".equals(ctx.getText())));
    }

    @Override
    public void exitLeia(LeiaContext ctx) {
        valores.insira(ctx, leia);
    }

    @Override
    public void exitSe(SeContext ctx) {
        final Bool condicao = valores.pegue(ctx.booleano());
        final Comando entao = valores.pegue(ctx.comando(0));
        final Comando senao = valores.pegue(ctx.comando(1));
        valores.insira(ctx, new Se(condicao, entao, senao));
    }

    @Override
    public void exitInteiro(InteiroContext ctx) {
        valores.insira(ctx, new Inteiro(parseInt(ctx.getText())));
    }

    @Override
    public void exitSkip(SkipContext ctx) {
        valores.insira(ctx, skip);
    }

    @Override
    public void exitEscreva(EscrevaContext ctx) {
        final Expressao exp = valores.pegue(ctx.expressao());
        valores.insira(ctx, new Escreva(exp));
    }

    @Override
    public void exitPrograma(ProgramaContext ctx) {
        final List<Comando> cmds = valores.pegue(ctx.seqComando());
        programa = new Programa(cmds);
        valores.insira(ctx, programa);
    }

    @Override
    public void exitId(IdContext ctx) {
        valores.insira(ctx, new Id(ctx.ID().getText()));
    }

    @Override
    public void exitSeqComando(SeqComandoContext ctx) {
        final List<Comando> comandos = new ArrayList<>();
        for (ComandoContext comando : ctx.comando()) {
            comandos.add(valores.pegue(comando));
        }
        valores.insira(ctx, comandos);
    }

    @Override
    public void exitAtribuicao(AtribuicaoContext ctx) {
        final String id = ctx.ID().getText();
        final Expressao exp = valores.pegue(ctx.expressao());
        valores.insira(ctx, new Atribuicao(id, exp));
    }

    @Override
    public void exitBloco(BlocoContext ctx) {
        final List<Comando> cmds = valores.pegue(ctx.seqComando());
        valores.insira(ctx, new Bloco(cmds));
    }

    /**
     * Obtém os operandos de uma operação binária.
     */
    private Expressao[] obterOperandos(ParserRuleContext ctx, ExpressaoContext esquerda,
            ExpressaoContext direita) {
        return new Expressao[] {
            valores.pegue(esquerda),
            valores.pegue(direita)
        };
    }

    /**
     * Obtém o operador textual.
     */
    private String obterOperador(ParserRuleContext ctx) {
        return ctx.getChild(1).getText();
    }

    @Override
    public void exitOpBin(OpBinContext ctx) {
        final Expressao[] operandos = obterOperandos(
                ctx,
                ctx.expressao(0),
                ctx.expressao(1));

        final String op = obterOperador(ctx);

        final Expressao exp = switch (op) {
            case "*" -> new ExpMult(operandos[0], operandos[1]);
            case "-" -> new ExpSub(operandos[0], operandos[1]);
            default -> new ExpSoma(operandos[0], operandos[1]);
        };

        valores.insira(ctx, exp);
    }

    @Override
    public void exitEnquanto(EnquantoContext ctx) {
        final Bool condicao = valores.pegue(ctx.booleano());
        final Comando comando = valores.pegue(ctx.comando());
        valores.insira(ctx, new Enquanto(condicao, comando));
    }

    @Override
    public void exitELogico(ELogicoContext ctx) {
        final Bool esq = valores.pegue(ctx.booleano(0));
        final Bool dir = valores.pegue(ctx.booleano(1));
        valores.insira(ctx, new ELogico(esq, dir));
    }

    @Override
    public void exitBoolPar(BoolParContext ctx) {
        valores.insira(ctx, valores.pegue(ctx.booleano()));
    }

    @Override
    public void exitNaoLogico(NaoLogicoContext ctx) {
        valores.insira(ctx, new NaoLogico(valores.pegue(ctx.booleano())));
    }

    @Override
    public void exitExpPar(ExpParContext ctx) {
        valores.insira(ctx, valores.pegue(ctx.expressao()));
    }

    @Override
    public void exitExiba(ExibaContext ctx) {
        final String texto = ctx.TEXTO().getText();
        valores.insira(ctx, new Exiba(texto.substring(1, texto.length() - 1)));
    }

    @Override
    public void exitOpRel(OpRelContext ctx) {
        final Expressao[] operandos = obterOperandos(
                ctx,
                ctx.expressao(0),
                ctx.expressao(1));

        final String op = obterOperador(ctx);

        final Bool exp = switch (op) {
            case "=" -> new ExpIgual(operandos[0], operandos[1]);
            case "<=" -> new ExpMenorIgual(operandos[0], operandos[1]);
            default -> new ExpIgual(operandos[0], operandos[0]);
        };

        valores.insira(ctx, exp);
    }
}