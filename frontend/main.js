function toggleMobileMenu(btn){
  const menu = document.getElementById("mobileMenu");
  const expanded = btn.getAttribute("aria-expanded") === "true";

  if(expanded){
    btn.setAttribute("aria-expanded", "false");
    menu.style.display = "none";
  } else {
    btn.setAttribute("aria-expanded", "true");
    menu.style.display = "block";
  }
}


window.addEventListener("resize", () => {
  const menu = document.getElementById("mobileMenu");
  const burger = document.querySelector(".burger");

  if(window.innerWidth > 900){
    menu.style.display = "none";
    if(burger) burger.setAttribute("aria-expanded", "false");
  }
});
document.addEventListener('DOMContentLoaded', () => {
  const startBtn = document.getElementById("startBtn");
  const modal = document.getElementById("applyModal");
  const closeBtn = document.getElementById("applyClose");
  const backdrop = document.querySelector(".apply-backdrop");
  const applyForm = document.getElementById("applyForm");

  if (!modal) return;

  function openModal() {
    modal.classList.add("show");
    document.body.style.overflow = 'hidden'; 
  }

  function closeModal() {
    modal.classList.remove("show");
    document.body.style.overflow = '';


  }

  if (startBtn) startBtn.addEventListener("click", openModal);
  if (closeBtn) closeBtn.addEventListener("click", closeModal);
  if (backdrop) backdrop.addEventListener("click", closeModal);


  document.addEventListener("keydown", (e) => {
    if (e.key === "Escape" && modal.classList.contains("show")) closeModal();
  });


  if (applyForm) {
    applyForm.addEventListener("submit", (e) => {
      e.preventDefault();

     alert("Your application has been submitted!");

      closeModal();
    });
  }
});
let total = 0;

async function loadCourses() {
  const res = await fetch("http://localhost:8080/skills");
  const data = await res.json();

  const container = document.getElementById("courses-container");
  if (!container) return;

  container.innerHTML = "";

  data.forEach(skill => {
    const price = 10000;

    const card = document.createElement("div");
    card.className = "course-card";
    card.innerHTML = `
      <h3>${skill.name}</h3>
      <p>Build your skills step by step</p>
      <p><b>${price} KZT</b></p>
      <button onclick="addToPath('${skill.name}', ${price})">Add to Path</button>
    `;
    container.appendChild(card);
  });
}

function addToPath(name, price) {
  const list = document.getElementById("path-list");
  if (!list) return;

  const li = document.createElement("li");
  li.textContent = name;
  list.appendChild(li);

  total += price;
  document.getElementById("total-price").innerText = "Total: " + total + " KZT";
}

window.addEventListener("DOMContentLoaded", loadCourses);

document.addEventListener("DOMContentLoaded", () => {
  document.querySelectorAll(".animate").forEach(el => {
    el.classList.add("show");
  });
});

