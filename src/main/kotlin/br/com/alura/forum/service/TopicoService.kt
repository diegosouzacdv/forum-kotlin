package br.com.alura.forum.service

import br.com.alura.forum.dto.AtualizacaoTopicoForm
import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.dto.TopicoPorCategoriaDto
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.repository.TopicoRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.data.domain.Page
import java.util.stream.Collectors

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Tópico não encontrado"
) {

    /*    init {
            val topico = Topico(
                id = 1, titulo = "Duvida Kotlin", mensagem = "Variaveis no Kotlin",
                curso = Curso(
                    id = 1, nome = "kotlin", categoria = "Programação"
                ),
                autor = Usuario(
                    id = 1, nome = "Ana da Silva", email = "ana@email.com"
                )
            )

            val topico2 = Topico(
                id = 2, titulo = "Duvida Kotlin 2", mensagem = "Variaveis no Kotlin 2",
                curso = Curso(
                    id = 1, nome = "kotlin", categoria = "Programação"
                ),
                autor = Usuario(
                    id = 1, nome = "Ana da Silva", email = "ana@email.com"
                )
            )

            val topico3 = Topico(
                id = 3, titulo = "Duvida Kotlin 3", mensagem = "Variaveis no Kotlin 3",
                curso = Curso(
                    id = 1, nome = "kotlin", categoria = "Programação"
                ),
                autor = Usuario(
                    id = 1, nome = "Ana da Silva", email = "ana@email.com"
                )
            )

            topicos = listOf(topico, topico2, topico3) // mesma coisa de Arrays.asList(topico, topico, topico)
        }*/


    fun listar(nomeCurso: String?, paginacao: Pageable):Page<TopicoView> {
        val topicos = if(nomeCurso == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByCursoNomeIgnoreCase(nomeCurso, paginacao)
        }
        return topicos
            .map { t ->
                topicoViewMapper.map(t)
            }
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = repository.findById(id)
            .orElseThrow { NotFoundException(notFoundMessage) }
        return topicoViewMapper.map(topico)


    }

    fun cadastrar(form: NovoTopicoForm): TopicoView {
        var topico = topicoFormMapper.map(form)
        topico = repository.save(topico)
        return topicoViewMapper.map(topico)
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {

        val topico = repository.findById(form.id).orElseThrow { NotFoundException(notFoundMessage) }
        topico.titulo = form.titulo
        topico.mensagem = form.mensagem
        return topicoViewMapper.map(topico)
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }

    fun relatorio(): List<TopicoPorCategoriaDto> {
        return repository.relatorio()
    }


}
