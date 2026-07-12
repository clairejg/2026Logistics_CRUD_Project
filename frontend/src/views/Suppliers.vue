<template>
  <div>
    <h2>Suppliers</h2>
    <div class="form-grid">
      <input v-model="form.name" placeholder="Name" />
      <input v-model="form.contactEmail" placeholder="Email" />
      <input v-model="form.phone" placeholder="Phone" />
      <input v-model="form.address" placeholder="Address" />
      <button class="btn-primary" @click="save">{{ editing ? 'Update' : 'Add' }}</button>
    </div>
    <table>
      <thead>
        <tr><th>ID</th><th>Name</th><th>Email</th><th>Phone</th><th>Address</th><th>Actions</th></tr>
      </thead>
      <tbody>
        <tr v-for="s in suppliers" :key="s.id">
          <td>{{ s.id }}</td><td>{{ s.name }}</td><td>{{ s.contactEmail }}</td>
          <td>{{ s.phone }}</td><td>{{ s.address }}</td>
          <td>
            <button class="btn-edit" @click="edit(s)">Edit</button>
            <button class="btn-danger" @click="remove(s.id)" style="margin-left:6px">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { SUPPLIER_BASE_URL } from '@/config/apiConfig'

const API = `${SUPPLIER_BASE_URL}/suppliers`
const suppliers = ref([])
const editing = ref(false)
const form = ref({ id: null, name: '', contactEmail: '', phone: '', address: '' })

const load = async () => { suppliers.value = await fetch(API).then(r => r.json()) }

const save = async () => {
  const opts = { method: editing.value ? 'PUT' : 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(form.value) }
  await fetch(editing.value ? `${API}/${form.value.id}` : API, opts)
  reset(); load()
}

const edit = (s) => { form.value = { ...s }; editing.value = true }
const remove = async (id) => { await fetch(`${API}/${id}`, { method: 'DELETE' }); load() }
const reset = () => { form.value = { id: null, name: '', contactEmail: '', phone: '', address: '' }; editing.value = false }

onMounted(load)
</script>
