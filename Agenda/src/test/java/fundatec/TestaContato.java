package fundatec;

import controller.ContatoController;
import exception.ParametroInvalidoException;
import model.Contato;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestaContato {
   /* estância da classe pra poder acessar os métodos dela*/
    ContatoController controller = new ContatoController();

    @Test
    void testaAdicionarContatoExceptionNome(){
        ParametroInvalidoException ex = assertThrows(
                ParametroInvalidoException.class,
                () -> controller.adicionarContato(null,"987467729","tally0machado@gmail.com",LocalDate.of(2006,6,6)),
                "Exception não lançada quando um valor está nulo"
        );

        assertTrue(ex.getMessage().contains("O campo nome é obrigatório"), "A mensagem de erro esta errada");

    }

    @Test
    void testaAdicionarContatoExceptionTelefone(){
        ParametroInvalidoException ex = assertThrows(
                ParametroInvalidoException.class,
                () -> controller.adicionarContato("Tallyta",null,"tally0machado@gmail.com",LocalDate.of(2006,6,6)),
                "Exception não lançada quando um valor está nulo"
        );

        assertTrue(ex.getMessage().contains("O campo telefone é obrigatório"), "A mensagem de erro esta errada");

    }

    @Test
    void testaAdicionarContatoExceptionDtNascimento(){
        ParametroInvalidoException ex = assertThrows(
                ParametroInvalidoException.class,
                () -> controller.adicionarContato("Tallyta","987467729","tally0machado@gmail.com",null),
                "Exception não lançada quando um valor está nulo"
        );

        assertTrue(ex.getMessage().contains("O campo data de nascimento é obrigatória"), "A mensagem de erro esta errada");

    }

    @Test
    void testaAdicionarContato() throws ParametroInvalidoException {
        controller.adicionarContato("Ana", "123456789", "ana@email.com", LocalDate.of(2000, 5, 20));
        List<Contato> contatos = controller.listarContatos();

        assertEquals(1, contatos.size());
        assertEquals("Ana", contatos.get(0).getNome());
    }

    @Test
    void testaRemoverContato() throws ParametroInvalidoException {
        controller.adicionarContato("Ana", "123456789", "ana@email.com", LocalDate.of(2000, 5, 20));
        int id = controller.listarContatos().get(0).getId();

        assertTrue(controller.removerContato(id));
        assertTrue(controller.listarContatos().isEmpty());
    }

    @Test
    void testaEditarContato() throws ParametroInvalidoException {
        controller.adicionarContato("Ana", "123456789", "ana@email.com", LocalDate.of(2000, 5, 20));
        int id = controller.listarContatos().get(0).getId();

        controller.editarContato(id, "Ana Maria", "987654321", "ana.maria@email.com", LocalDate.of(2000, 5, 20));

        Contato contatoEditado = controller.buscarContatoPorId(id);
        assertEquals("Ana Maria", contatoEditado.getNome());
        assertEquals("987654321", contatoEditado.getTelefone());
    }

    @Test
    void testaPesquisarPorLetra() throws ParametroInvalidoException {
        controller.adicionarContato("Ana", "123456789", "ana@email.com", LocalDate.of(2000, 5, 20));
        controller.adicionarContato("Tally", "987654321", "tally@email.com", LocalDate.of(1999, 3, 15));

        List<Contato> resultados = controller.pesquisarPorLetra("A");

        assertEquals(1, resultados.size());
        assertEquals("Ana", resultados.get(0).getNome());
    }

    @Test
    void testaListarAniversariantesDoMes() throws ParametroInvalidoException {
        controller.adicionarContato("Carlos", "98767678900", "carlos@email.com", LocalDate.of(2002, 8, 15));
        controller.adicionarContato("Ana", "997856789", "ana@email.com", LocalDate.of(2000, 5, 20));
        controller.adicionarContato("Tally", "911354321", "tally@email.com", LocalDate.of(1999, 5, 10));

        List<Contato> aniversariantes = controller.listarAniversariantesDoMes(5);

        assertEquals(2, aniversariantes.size());
        assertEquals("Tally", aniversariantes.get(0).getNome());
        assertEquals("Ana", aniversariantes.get(1).getNome());
    }
}
