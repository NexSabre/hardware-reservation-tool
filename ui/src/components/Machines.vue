<template>
  <div>
      <h1> Hardware Reservation Tool</h1>
        {{ allMachines }} <br>
        <h2> Enabled machines </h2>

       <div class="row">
           <div class="col-sm-3"></div>
           <div class="col-sm-6">
           <div >
               <machine-cards v-for="machine in machines" :key="machine.id" :machine="machine"></machine-cards>
           </div>
        </div>
            <div class="col-sm-3"></div>

       </div>
  </div>
</template>

<script>
import Constants from './Constants.js'
import MachineCards from './MachineCards'

export default {
    name: "Machines",
    data() {
        return {
            machines: []
        }
    },
    components: {
        MachineCards
    },
    methods: {
        getMachines() {
            const baseURI = Constants.machinesURI
            this.$http.get(baseURI)
                .then(result => {
                    result.data.forEach(item => {
                        this.machines.push(item)
                    })
                })
            console.log(this.machines)
        }
    }, 
    mounted() {
        this.getMachines()
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