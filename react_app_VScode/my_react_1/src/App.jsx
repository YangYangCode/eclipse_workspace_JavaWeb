import './App.css'

const MyHello = () => {
  return <h1>Hello React</h1>
};

const MyWellcome = () => {
  const text = 'John'
  return <h1>Welcome {text} !</h1>
};

const MyGreet = (props) => {
  const name = props.name
  const age = props.age
  return <h1>Hi {name} {age} !</h1>
};

const getBmiStatus = (bmi) => {
  return bmi < 18? "過輕" : bmi > 23? "過重" : "正常";
}

const MyBMI = (props) => {
  const {username, userage, h, w} = props;
  const bmi = (w/ (h*h)).toFixed(2);
  // 請加入 BMI 判斷, BMI =>   過輕 < 18-23 > 過重, 正常
  return (
          <div>
            <h1>
            {username}, your are {userage}
            </h1>
            <h2>
              BMI: {bmi}, {getBmiStatus(bmi)}
            </h2>
          </div>
        );
};

function App() {


  return (
    <>
        <MyHello />
        <MyWellcome />
        <MyGreet name="Mary" age="18" />
        <MyBMI username="Mary" userage="18" h="1.6" w="45" />
    </>
  )
}

export default App
