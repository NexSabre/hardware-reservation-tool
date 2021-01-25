<template>
  <div v-if="v">
    <h1> Hardware Reservation Tool</h1>
    {{ allMachines }} <br>
    <h2> Enabled machines </h2>
    <div class="row">
      <div class="col-sm-3"></div>
      <div class="col-sm-6">
        <div>
          <machine-cards v-for="machine in machines" :key="machine.id" :machine="machine"></machine-cards>
        </div>
      </div>
      <div class="col-sm-3"></div>

    </div>
  </div>
  <div v-else>
    <!-- TODO @NexSabre extends this with loading screen, before promise will be resolved -->
    <h1>Connection issue with server?</h1>
  </div>
</template>

<script>
import Constants from './Constants.js'
import MachineCards from './MachineCards'
import Machine from './models/Machine.js'

export default {
  name: "Machines",
  data() {
    return {
      v: false,
      machines: []
    }
  },
  components: {
    MachineCards
  },
  methods: {
    checkConnection() {
      const baseURI = "http://localhost:8080/api/v1/info"
      this.$http
          .get(baseURI)
          .then(result => {
            console.log(result.status)
            this.v = result.status === 200;
          })
          .catch(err => {
            console.log("Error: " + err)
            this.v = false
          })
    },
    getMachines() {
      const baseURI = Constants.machinesURI
      this.$http
          .get(baseURI)
          .then(result => {
            result.data.forEach(item => {
              this.machines.push(new Machine(item))
            })
          })
          .cat
    }
  },
  mounted() {
    this.checkConnection();
    this.getMachines();
  },
  computed: {
    allMachines() {
      const machinesCount = this.machines.length
      return `Registered ${machinesCount} ${machinesCount > 1 ? "machines" : "machine"}`
    }
  }
}
</script>

<style>

</style>