<template>
  <div>
    <h2>Company Info</h2>
    <div class="form-grid">
      <input v-model="form.companyName" placeholder="Company Name" />
      <input v-model="form.address" placeholder="Address" />
      <input v-model="form.country" placeholder="Country" />
      <input v-model="form.phone" placeholder="Phone" />
      <button class="btn-primary" @click="save">{{ editing ? 'Update' : 'Add' }}</button>
      <button v-if="editing" @click="reset" style="background:#888;color:white;padding:8px 16px;border:none;border-radius:4px;cursor:pointer">Cancel</button>
    </div>
    <table>
      <thead>
        <tr>
          <th>Account ID</th>
          <th>Company Name</th>
          <th>Address</th>
          <th>Country</th>
          <th>Phone</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="c in companies" :key="c.accountId">
          <td>{{ c.accountId }}</td>
          <td>{{ c.companyName }}</td>
          <td>{{ c.address }}</td>
          <td>{{ c.country }}</td>
          <td>{{ c.phone }}</td>
          <td>
            <button class="btn-edit" @click="edit(c)">Edit</button>
            <button class="btn-danger" @click="remove(c.accountId)" style="margin-left:6px">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const API = 'http://localhost:8081/api/companies'
const companies = ref([])
const editing = ref(false)
const form = ref({ accountId: null, companyName: '', address: '', country: '', phone: '' })

const load = async () => { companies.value = (await axios.get(API)).data }

const save = async () => {
  if (editing.value) {
    await axios.put(`${API}/${form.value.accountId}`, form.value)
  } else {
    await axios.post(API, form.value)
  }
  reset(); load()
}

const edit = (c) => { form.value = { ...c }; editing.value = true }
const remove = async (id) => { await axios.delete(`${API}/${id}`); load() }
const reset = () => { form.value = { accountId: null, companyName: '', address: '', country: '', phone: '' }; editing.value = false }

onMounted(load)
</script>
