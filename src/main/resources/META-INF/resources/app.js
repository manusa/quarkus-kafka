import {h, htm, render, useState, useEffect} from './lib/deps.js';
const html = htm.bind(h);

const MOUNTS_API = 'mounts';

const mountState = [];

const processMount = async mount => {
  const headers = new Headers();
  headers.set('Content-Type', 'text/plain')
 await fetch(MOUNTS_API,{
   method: 'POST',
   headers,
   body: mount
 });
};

const Title = ({children}) => (html`
  <h1 class='mt-2 text-3xl leading-8 font-extrabold tracking-tight text-gray-900 sm:text-4xl sm:leading-10'>
    ${children}
  </h1>
`);

const Subtitle = ({children}) => (html`
  <h2 class='text-base leading-6 text-indigo-600 font-semibold tracking-wide uppercase'>
    ${children}
  </h2>
`);

const Input = () => {
  const [value, setValue] = useState('');
  const onKeyDown = ({key, target: {value}}) => {
    if (key === 'Enter') {
      processMount(value);
      setValue('');
    } else {
      setValue(value);
    }
  }
  return (html`
    <input
     class='block mx-auto rounded rounded-md shadow-sm py-1 px-2 border border-gray-500 w-4/5 lg:w-3/5'
     value=${value}
     onKeyDown=${onKeyDown}
     placeholder='Mount'
    />
  `);
}

const MountCard = ({name, image, elevation}) => (html`
  <div class='w-full md:w-1/2 lg:w-1/3 p-4'>
    <div class='rounded overflow-hidden shadow-lg border border-gray-300'>
      <div
        class='h-64 flex-none bg-cover rounded-t text-center overflow-hidden'
        style=${`background-image: url('${image}')`}
        title=${name}
      >
      </div>
      <div class='px-6 py-4'>
        <div class='font-bold text-xl'>${name}</div>
        <div class='mt-2 text-sm'><strong>Elevation*:</strong> ${elevation} m</div>
      </div>
    </div>
  </div>
`);

const App = () => {
  const [mounts, setMounts] = useState([]);
  const [polling, setPolling] = useState(false);
  useEffect(() => {
    if (!polling) {
      const es = new EventSource(MOUNTS_API);
      es.onopen = () => mountState.length = 0;
      es.onmessage = ({data}) => {
        mountState.unshift(JSON.parse(data));
        setMounts([...mountState]);
      };
      setPolling(true);
    }
  }, [setMounts]);
  return (html`
    <div class='py-12 bg-white'>
      <div class='max-w-screen-xl mx-auto px-4 sm:px-6 lg:px-8'>
        <div class='text-center'>
          <${Subtitle}>Quarkus Kafka Demo</Subtitle>
          <${Title}>Mounts Processing</Title>
          <p class='mt-4 text-xl text-gray-600'>
            Get pictures of your favorite mounts.
          </p>
        </div>
        <div class='mt-8 relative'>
          <${Input} />
        </div>
        <div class='-p-4 relative flex flex-wrap'>
          ${mounts.map((mount, idx) => (html`<${MountCard} key=${idx} ...${mount} />`))}
        </div>
        ${mounts.length > 0 && (html`<div class='text-xs'>
          *Elevation figure is completely random and inaccurate.
        </div>`)}
      </div>
    </div>
  `);
};

render(html`<${App} />`, document.querySelector('.quarkus-kafka-demo'));
