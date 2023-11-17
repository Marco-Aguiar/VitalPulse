package med.vitaPulse.api.paciente;

import jakarta.validation.constraints.NotNull;
import med.vitaPulse.api.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
