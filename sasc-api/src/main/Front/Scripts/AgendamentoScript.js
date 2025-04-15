// Elementos do calendário
const calendarBody = document.querySelector('#calendar tbody');
const monthYear = document.getElementById('month-year');
const prevBtn = document.getElementById('prev-month');
const nextBtn = document.getElementById('next-month');
let currentDate = new Date();

// Renderiza o calendário
function renderCalendar(date) {
  calendarBody.innerHTML = '';
  const year = date.getFullYear();
  const month = date.getMonth();
  const firstDay = new Date(year, month, 1).getDay();
  const daysInMonth = new Date(year, month + 1, 0).getDate();

  // Título com mês e ano
  monthYear.textContent = date.toLocaleString('pt-BR', { month: 'long', year: 'numeric' });

  let row = document.createElement('tr');
  for (let i = 0; i < firstDay; i++) {
    row.appendChild(document.createElement('td'));
  }

  // Preenche os dias
  for (let day = 1; day <= daysInMonth; day++) {
    if (row.children.length === 7) {
      calendarBody.appendChild(row);
      row = document.createElement('tr');
    }
    const cell = document.createElement('td');
    cell.textContent = day;
    cell.addEventListener('click', () => {
      document.querySelectorAll('#calendar td').forEach(td => td.classList.remove('selected'));
      cell.classList.add('selected');
    });
    row.appendChild(cell);
  }

  calendarBody.appendChild(row);
}

// Botões de navegação
prevBtn.addEventListener('click', () => {
  currentDate.setMonth(currentDate.getMonth() - 1);
  renderCalendar(currentDate);
});

nextBtn.addEventListener('click', () => {
  currentDate.setMonth(currentDate.getMonth() + 1);
  renderCalendar(currentDate);
});

// Inicializa o calendário
renderCalendar(currentDate);

// Seleção de horário
document.querySelectorAll('.horarios button').forEach(btn => {
  btn.addEventListener('click', () => {
    document.querySelectorAll('.horarios button').forEach(b => b.classList.remove('selected'));
    btn.classList.add('selected');
  });
});

// Upload de imagem de avatar
const avatarInput = document.getElementById('avatar-input');
const avatarImg = document.getElementById('avatar-img');

avatarInput.addEventListener('change', (event) => {
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = (e) => {
      avatarImg.src = e.target.result;
    };
    reader.readAsDataURL(file);
  }
});

// Simula o carregamento de nome e setor após login
document.addEventListener('DOMContentLoaded', () => {
  const userName = localStorage.getItem('userName') || 'Usuário Exemplo';
  const userSector = localStorage.getItem('userSector') || 'Setor X';
  document.getElementById('user-name').textContent = userName;
  document.getElementById('user-sector').textContent = userSector;
});


// Função para exibir modal
function mostrarModal(id) {
  document.getElementById(id).style.display = 'flex';
}

// Função para fechar modal
function fecharModal(id) {
  document.getElementById(id).style.display = 'none';
}

// Evento ao clicar em "CONFIRMAR"
document.querySelector('.confirmar').addEventListener('click', () => {
  const horarioSelecionado = document.querySelector('.horarios button.selected');
  const diaSelecionado = document.querySelector('#calendar td.selected');

  // Verificação: precisa selecionar data e horário
  if (!horarioSelecionado || !diaSelecionado) {
    const erroMsg = "❌ Falha ao fazer o agendamento.<br><strong>Motivo:</strong> Selecione uma data e horário.";
    document.querySelector('#modal-erro p').innerHTML = erroMsg;
    mostrarModal('modal-erro');
    return;
  }

  // Simulação de sucesso ou erro (70% de chance de sucesso)
  const sucesso = Math.random() > 0.3;

  if (sucesso) {
    mostrarModal('modal-sucesso');
  } else {
    const erroMsg = "❌ Falha ao fazer o agendamento.<br><strong>Motivo:</strong> Horário já reservado.";
    document.querySelector('#modal-erro p').innerHTML = erroMsg;
    mostrarModal('modal-erro');
  }
});

// Fecha o modal ao clicar fora do conteúdo
window.addEventListener('click', function (e) {
  const sucessoModal = document.getElementById('modal-sucesso');
  const erroModal = document.getElementById('modal-erro');

  if (e.target === sucessoModal) fecharModal('modal-sucesso');
  if (e.target === erroModal) fecharModal('modal-erro');
});
