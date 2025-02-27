function runAlgorithm() {
    let algorithm = document.getElementById("algorithm").value;
    let requests = document.getElementById("requests").value.split(',').map(Number);
    let head = parseInt(document.getElementById("head").value);
    let size = parseInt(document.getElementById("size").value);
    let direction = parseInt(document.getElementById("direction").value);

    if (requests.includes(NaN) || isNaN(head)) {
        alert("Please enter valid inputs.");
        return;
    }

    let totalMovement = 0;
    let sequence = [];

    if (algorithm === "SSTF") {
        totalMovement = sstf(requests, head, sequence);
    } else if (algorithm === "SCAN") {
        totalMovement = scan(requests, head, size, direction, sequence);
    } else if (algorithm === "CLOOK") {
        totalMovement = clook(requests, head, direction, sequence);
    }

    document.getElementById("result").innerText = "Total Head Movement: " + totalMovement;
    drawGraph(sequence);
}

function sstf(requests, head, sequence) {
    let totalMovement = 0;
    let visited = new Array(requests.length).fill(false);

    for (let i = 0; i < requests.length; i++) {
        let minDistance = Infinity, minIndex = -1;
        for (let j = 0; j < requests.length; j++) {
            if (!visited[j] && Math.abs(requests[j] - head) < minDistance) {
                minDistance = Math.abs(requests[j] - head);
                minIndex = j;
            }
        }
        visited[minIndex] = true;
        totalMovement += minDistance;
        head = requests[minIndex];
        sequence.push(head);
    }
    return totalMovement;
}

function scan(requests, head, size, direction, sequence) {
    requests.push(0, size - 1);
    requests.sort((a, b) => a - b);
    let totalMovement = 0;
    let index = requests.findIndex(req => req >= head);

    if (direction === 1) {
        for (let i = index; i < requests.length; i++) {
            totalMovement += Math.abs(head - requests[i]);
            head = requests[i];
            sequence.push(head);
        }
        for (let i = index - 1; i >= 0; i--) {
            totalMovement += Math.abs(head - requests[i]);
            head = requests[i];
            sequence.push(head);
        }
    } else {
        for (let i = index - 1; i >= 0; i--) {
            totalMovement += Math.abs(head - requests[i]);
            head = requests[i];
            sequence.push(head);
        }
        for (let i = index; i < requests.length; i++) {
            totalMovement += Math.abs(head - requests[i]);
            head = requests[i];
            sequence.push(head);
        }
    }
    return totalMovement;
}

function clook(requests, head, direction, sequence) {
    requests.sort((a, b) => a - b);
    let totalMovement = 0;
    let index = requests.findIndex(req => req >= head);

    if (direction === 1) {
        for (let i = index; i < requests.length; i++) {
            totalMovement += Math.abs(head - requests[i]);
            head = requests[i];
            sequence.push(head);
        }
        for (let i = 0; i < index; i++) {
            totalMovement += Math.abs(head - requests[i]);
            head = requests[i];
            sequence.push(head);
        }
    } else {
        for (let i = index - 1; i >= 0; i--) {
            totalMovement += Math.abs(head - requests[i]);
            head = requests[i];
            sequence.push(head);
        }
        for (let i = requests.length - 1; i >= index; i--) {
            totalMovement += Math.abs(head - requests[i]);
            head = requests[i];
            sequence.push(head);
        }
    }
    return totalMovement;
}

function drawGraph(sequence) {
    let canvas = document.getElementById("graph");
    let ctx = canvas.getContext("2d");
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    ctx.beginPath();
    ctx.moveTo(50, 50);
    sequence.forEach((pos, i) => {
        ctx.lineTo(50 + i * 40, 50 + pos * 2);
    });
    ctx.stroke();
}
